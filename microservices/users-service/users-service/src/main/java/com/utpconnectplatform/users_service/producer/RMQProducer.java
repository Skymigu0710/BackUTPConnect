package com.utpconnectplatform.users_service.producer;

import com.utpconnectplatform.users_service.config.RabbitMQConfig;
import com.utpconnectplatform.users_service.model.Follow_request;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/follow")
    public Follow_request follow(@RequestBody Follow_request followRequest){
        // Enviar el request a la cola RabbitMQ con los IDs del seguidor y seguido
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, followRequest);

        return followRequest;
    }
}
