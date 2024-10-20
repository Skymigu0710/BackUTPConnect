package com.utpconnectplatform.authentication_service.Service;

import com.utpconnectplatform.authentication_service.Config.RabbitMQConfig;
import com.utpconnectplatform.authentication_service.DTO.UserDto;
import com.utpconnectplatform.authentication_service.Jwt.JwtService;
import com.utpconnectplatform.authentication_service.model.AuthResponse;
import com.utpconnectplatform.authentication_service.model.Authentication;
import com.utpconnectplatform.authentication_service.model.RegisterRequest;
import com.utpconnectplatform.authentication_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service

public class AuthService {
    @Autowired
    private  RabbitTemplate rabbitTemplate;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Userservice userservice;
    @Autowired
    public AuthService(JwtService jwtService, AuthenticationManager authenticationManager, Userservice userservice) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userservice = userservice;
    }

    public AuthResponse login (UserDto request){
        try {
            // Autentica al usuario con el Username y Password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciales inválidas"); // Manejo de excepciones
        }
// Obtén los detalles del usuario
        UserDto user = userservice.getUserByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder().token(token).userDetails(user).build();
    }

    public AuthResponse register (RegisterRequest request){
        //Se crea un nuevo objeto
        User newUser = new User(request.getName(), request.getLast_name(),request.getUsername(), request.getEmail(), request.getPassword(),request.getRole());

        //Publicar un mensaje en RabbitMQ
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTING_KEY,newUser);
        //devolvemos la respuesta de registro exitoso
        return AuthResponse.builder().token(jwtService.getToken(newUser)).build();
    }

}
