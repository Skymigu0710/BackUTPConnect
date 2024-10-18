package com.utpconnectplatform.authentication_service.Service;

import com.utpconnectplatform.authentication_service.Config.RabbitMQConfig;
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
    @Autowired
    public AuthService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public AuthResponse login (Authentication request){
        return null;
    }
    public AuthResponse register (RegisterRequest request){
        //Se crea un nuevo objeto
        User newUser = new User(request.getName(), request.getLast_name(), request.getEmail(), request.getPassword(),request.getRole());

        //Publicar un mensaje en RabbitMQ
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTING_KEY,newUser);
        //devolvemos la respuesta de registro exitoso
        return new AuthResponse("Registro EXITOSO");
    }

}
