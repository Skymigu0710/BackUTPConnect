package com.utpconnectplatform.follows_service.service;


import com.utpconnectplatform.follows_service.DTO.UserIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    public UserIdDto getUserById(Long userId) {
        String url = "http://localhost:8082/api/users/" + userId; // Ajusta la URL según tu configuración
        return restTemplate.getForObject(url, UserIdDto.class);
    }
}
