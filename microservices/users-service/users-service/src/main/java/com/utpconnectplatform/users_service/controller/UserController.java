package com.utpconnectplatform.users_service.controller;


import com.utpconnectplatform.users_service.model.Users;
import com.utpconnectplatform.users_service.service.UserService;
import com.utpconnectplatform.users_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElse(null);
    }
    @GetMapping("/name/{username}")
    public Users getUserByUserName(@PathVariable String username) {
        return userRepository.findByUsername(username).orElse(null);
    }



}
