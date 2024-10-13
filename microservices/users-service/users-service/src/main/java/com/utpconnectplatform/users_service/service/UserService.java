package com.utpconnectplatform.users_service.service;

import com.utpconnectplatform.users_service.model.Users;
import com.utpconnectplatform.users_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUserById(Long id) {
        Optional<Users> userOptional= userRepository.findById(id);
        if(userOptional.isPresent())
            return userOptional.get();
        else
            throw new RuntimeException("No se encontró el usuario uwu");
    }

    public Users getUserByName(String name) {
        Optional<Users> userOptional = userRepository.findByName(name);
        if(userOptional.isPresent())
            return userOptional.get();
        else
            throw new RuntimeException("No se encontró el usuario con el nombre: " + name);
    }

}
