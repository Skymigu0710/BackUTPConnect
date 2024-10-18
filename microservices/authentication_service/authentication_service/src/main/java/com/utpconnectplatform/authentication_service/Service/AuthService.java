package com.utpconnectplatform.authentication_service.Service;

import com.utpconnectplatform.authentication_service.Config.RabbitMQConfig;
import com.utpconnectplatform.authentication_service.Jwt.JwtService;
import com.utpconnectplatform.authentication_service.model.AuthResponse;
import com.utpconnectplatform.authentication_service.model.Authentication;
import com.utpconnectplatform.authentication_service.model.RegisterRequest;
import com.utpconnectplatform.authentication_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service

public class AuthService {

    private final  RabbitTemplate rabbitTemplate;
    private final JwtService jwtService;
    @Autowired
    public AuthService(RabbitTemplate rabbitTemplate, JwtService jwtService) {
        this.rabbitTemplate = rabbitTemplate;
        this.jwtService = jwtService;
    }

    public AuthResponse login (Authentication request){

        return null;
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
