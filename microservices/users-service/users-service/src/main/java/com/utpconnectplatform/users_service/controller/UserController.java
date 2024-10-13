package com.utpconnectplatform.users_service.controller;


import com.utpconnectplatform.users_service.model.Users;
import com.utpconnectplatform.users_service.service.UserService;
import com.utpconnectplatform.users_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }
    @GetMapping("/name/{name}")
    public Users getUserByName(@PathVariable String name) {
        return userRepository.findByName(name).orElse(null);
    }



}
