package com.utpconnectplatform.authentication_service.Service;

import com.utpconnectplatform.authentication_service.Config.RabbitMQConfig;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class AuthService {

    private final RabbitTemplate rabbitTemplate;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthService(RabbitTemplate rabbitTemplate, JwtService jwtService,
                       AuthenticationManager authenticationManager,
                       UserDetailsService userDetailsService) {
        this.rabbitTemplate = rabbitTemplate;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    // Método de login
    public AuthResponse login(Authentication request) {
        // Intentar autenticar al usuario
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUser(), request.getPass())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Si la autenticación es exitosa, cargar los detalles del usuario
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUser());

        // Generar el token JWT
        String jwtToken = jwtService.getToken(userDetails);

        // Devolver la respuesta con el token
        return AuthResponse.builder().token(jwtToken).build();
    }

    // Método de registro
    public AuthResponse register(RegisterRequest request) {
        // Se crea un nuevo objeto Usuario
        User newUser = new User(
                request.getName(),
                request.getLast_name(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );

        // Publicar un mensaje en RabbitMQ
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, newUser);

        // Devolvemos la respuesta de registro exitoso con un token JWT
        return AuthResponse.builder().token(jwtService.getToken(newUser)).build();
    }
}

