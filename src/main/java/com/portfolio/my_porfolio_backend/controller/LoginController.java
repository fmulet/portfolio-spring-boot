package com.portfolio.my_porfolio_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Importa aquí tu clase UserDto real
import com.portfolio.my_porfolio_backend.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto loginDto) {

        // 1. Autenticar usando los datos que vienen en tu UserDto
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(), // Ajusta si en tu DTO se llama diferente (ej: getEmail())
                        loginDto.getPassword()));

        // 2. Si la autenticación es exitosa, preparamos el DTO de retorno.
        loginDto.setPassword(null);

        return ResponseEntity.ok(loginDto);
    }

}
