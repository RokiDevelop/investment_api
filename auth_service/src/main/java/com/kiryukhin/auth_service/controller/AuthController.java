package com.kiryukhin.auth_service.controller;

import com.kiryukhin.auth_service.security.dto.RegistrationUserDto;
import com.kiryukhin.auth_service.security.dto.JwtRequest;
import com.kiryukhin.auth_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        return authService.createAuthToken(request);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
