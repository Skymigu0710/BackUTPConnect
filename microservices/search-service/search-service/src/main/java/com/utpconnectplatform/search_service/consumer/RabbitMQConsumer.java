package com.utpconnectplatform.search_service.consumer;

import com.utpconnectplatform.search_service.config.RabbitMQConfig;
import com.utpconnectplatform.search_service.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(User user){
        System.out.println("Mensaje: "+user);
    }
}
