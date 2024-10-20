package com.utpconnectplatform.follows_service.consumer;

import com.utpconnectplatform.follows_service.config.RabbitMQConfig;
import com.utpconnectplatform.follows_service.model.Follow_request;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    //cambiar cola
    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(Follow_request followRequest){
        System.out.println("Consumer is able to consume message from queue: " + followRequest);

    }
}
