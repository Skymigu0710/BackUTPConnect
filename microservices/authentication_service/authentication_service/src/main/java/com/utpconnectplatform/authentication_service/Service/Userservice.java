package com.utpconnectplatform.authentication_service.Service;

import com.utpconnectplatform.authentication_service.Config.RabbitMQConfig;
import com.utpconnectplatform.authentication_service.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class Userservice {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Userservice(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Optional<User> getUsername(String username) {
        // Envía un mensaje a RabbitMQ y espera la respuesta
        String queueName = "usernamesQueue"; // Nombre de la cola
        User response = (User) rabbitTemplate.convertSendAndReceive(queueName, username);

        // Devuelve la respuesta como un Optional
        return Optional.ofNullable(response); // Devuelve Optional vacío si la respuesta es null
    }

}
