package com.utpconnectplatform.users_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.utpconnectplatform.users_service.config.RabbitMQConfig;
import com.utpconnectplatform.users_service.model.Users;
import com.utpconnectplatform.users_service.repository.UserRepository;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private RabbitTemplate rabbitTemplate; // Inyecta RabbitTemplate
    public void sendMessage(Users username) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, username);
    }
    @Autowired
    private UserRepository userRepository;
    private final ObjectMapper objectMapper; // Declarar ObjectMapper
    Users users ;
    public UserService ( ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Users getUserById(Long id) {
        Optional<Users> userOptional= userRepository.findById(id);
        if(userOptional.isPresent())
            return userOptional.get();
        else
            throw new RuntimeException("No se encontró el usuario uwu");
    }


    public void listen(String username, Message message) throws UnsupportedEncodingException {
        System.out.println("Mensaje recibido: " + username);

        // Llama al método para obtener el usuario
        Users user = userRepository.findByUsername(username).orElse(null);
        System.out.println("Encabezados del mensaje: " + message.getMessageProperties().getHeaders());

        // Obtén el encabezado 'replyTo' y 'correlationId' del mensaje recibido
        String replyTo = message.getMessageProperties().getReplyTo();
        String correlationId = message.getMessageProperties().getCorrelationId();

        // Valida si el campo 'replyTo' está presente y no es la misma cola
        if (replyTo != null) {
            // Crea un nuevo mensaje con el 'correlationId' preservado
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType("application/json");
            messageProperties.setCorrelationId(correlationId);

            byte[] responseBody;
            if (user != null) {
                System.out.println("Usuario encontrado: " + user.getUsername() + "\nContraseña: " + user.getPassword());
                try {
                    // Serializa el objeto User a JSON
                    responseBody = objectMapper.writeValueAsString(user).getBytes("UTF-8"); // Usa UTF-8 para la codificació
                } catch (JsonProcessingException e) {
                    System.out.println("Error al convertir el objeto User a JSON: " + e.getMessage());
                    // Manejar el error adecuadamente, tal vez enviar un mensaje de error
                    responseBody = "Error al procesar el usuario".getBytes();
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }


            } else {
                System.out.println("Usuario no encontrado: " + username);
                responseBody = "{\"error\": \"Usuario no encontrado\"}".getBytes("UTF-8"); // Respuesta en JSON
            }

            // Crea el mensaje de respuesta
            Message responseMessage = new Message(responseBody, messageProperties);
            System.out.println("Enviando respuesta a " + replyTo + ": " + new String(responseBody, "UTF-8"));
            // Envía el mensaje de respuesta a la cola 'replyTo'
            System.out.printf("RESPONSE MESSAGE: " + responseMessage);
            try {
                rabbitTemplate.send(replyTo, responseMessage);
            } catch (AmqpException e) {
                System.out.println("Error al enviar el mensaje de respuesta: " + e.getMessage());
            }

        } else {
            System.out.println("No se puede enviar la respuesta, 'replyTo' no está presente o es la misma cola.");
        }
    }






    public Users getUserByUserName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(Users user) {
        // Validación para evitar la creación de mensajes infinitos
        if (user != null) {
            // Procesa el usuario
            System.out.println("Usuario recibido: " + user.getUsername());
            userRepository.save(user);
        }
    }

}
