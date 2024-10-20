package com.utpconnectplatform.authentication_service.controller;

import com.utpconnectplatform.authentication_service.Service.AuthService;
import com.utpconnectplatform.authentication_service.model.AuthResponse;
import com.utpconnectplatform.authentication_service.model.Authentication;
import com.utpconnectplatform.authentication_service.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class  AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    //incluye encabezados y cuerpo de respuesta
    public ResponseEntity <AuthResponse> login(@RequestBody Authentication request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
