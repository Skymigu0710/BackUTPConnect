package com.utpconnectplatform.authentication_service.Service;

import com.utpconnectplatform.authentication_service.Config.RabbitMQConfig;
import com.utpconnectplatform.authentication_service.DTO.UserDto;
import com.utpconnectplatform.authentication_service.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Service
public class Userservice {
    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<UserDto> getUserByUsername(String username) {
        String url = "http://localhost:8082/api/users/name/" + username; // Ajusta la URL según tu configuración
        try {
            // Realiza la llamada al microservicio
            ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);

            // Verifica si la respuesta es exitosa y contiene un cuerpo
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Optional.of(response.getBody());
            } else {
                // Si la respuesta no es exitosa, puedes manejar el caso aquí (opcional)
                return Optional.empty();
            }
        } catch (RestClientException e) {
            // Manejo de excepciones en caso de error en la llamada
            e.printStackTrace(); // Puedes registrar el error o lanzar una excepción personalizada
            return Optional.empty();
        }
    }
    //PARA RABBITMQ
    public Optional<User> getUsername(String username) {
        // Asegúrate de que el nombre de usuario no sea nulo o vacío
        if (username == null || username.isEmpty()) {
            System.out.println("El nombre de usuario proporcionado es inválido.");
            return Optional.empty(); // Retorna Optional vacío si el nombre de usuario no es válido
        }
        System.out.println("Enviando a RabbitMQ: " + username);

        String queueName = RabbitMQConfig.QUEUE; // Nombre de la cola
        // Crea una cola temporal para recibir la respuesta
        String replyQueue = rabbitTemplate.execute(channel -> channel.queueDeclare().getQueue());

// Envía el mensaje a 'users-service' con el nombre de la cola de respuesta temporal
        // Usar convertSendAndReceive para enviar el mensaje y recibir la respuesta
        try {
            User response = (User) rabbitTemplate.convertSendAndReceive(
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY,
                    username,
                    message -> {
                        message.getMessageProperties().setReplyTo(replyQueue); // Cola para la respuesta
                        return message;
                    }
            );

            // Verifica el contenido de la respuesta
            if (response != null) {
                System.out.println("Respuesta recibida para el usuario: " + response.getUsername());
            } else {
                System.out.println("No se recibió respuesta para el usuario: " + username);
            }
            return Optional.ofNullable(response); // Devuelve Optional vacío si la respuesta es null

        } catch (Exception e) {
            System.err.println("Error al enviar o recibir el mensaje: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        // Devuelve la respuesta como un Optional
    }

}
