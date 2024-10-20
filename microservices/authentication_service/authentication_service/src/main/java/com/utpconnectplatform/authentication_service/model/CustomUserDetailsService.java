package com.utpconnectplatform.authentication_service.model;

import com.utpconnectplatform.authentication_service.DTO.UserDto;
import com.utpconnectplatform.authentication_service.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final Userservice userService;

    @Autowired
    public CustomUserDetailsService(Userservice userService) {
        this.userService = userService;
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

