package com.utpconnect.groups_service.producer;

import com.utpconnect.groups_service.config.RabbitMQConfig;
import com.utpconnect.groups_service.model.Groups;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendGroupCreated(Groups group) {
        System.out.println("Sending Group Created Event: " + group);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, group);
    }

    public void sendGroupUpdated(Groups group) {
        System.out.println("Sending Group Updated Event: " + group);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, group);
    }

    public void sendGroupDeleted(Long groupId) {
        System.out.println("Sending Group Deleted Event for ID: " + groupId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, groupId);
    }
}
