package com.utpconnect.groups_service.consumer;

import com.utpconnect.groups_service.config.RabbitMQConfig;
import com.utpconnect.groups_service.model.Groups;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class GroupListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void listenForGroupEvents(Groups group) {
        System.out.println("Received Group Event: " + group);
        // Aquí puedes agregar la lógica que necesites para procesar el evento del grupo
    }
}
