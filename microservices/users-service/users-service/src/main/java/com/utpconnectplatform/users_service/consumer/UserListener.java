package com.utpconnectplatform.users_service.consumer;

import com.utpconnectplatform.users_service.config.RabbitMQConfig;
import com.utpconnectplatform.users_service.model.Users;
import com.utpconnectplatform.users_service.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener {

}
