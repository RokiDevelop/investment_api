package com.kiryukhin.auth_service.services;

import com.kiryukhin.auth_service.security.dto.JwtRequest;
import com.kiryukhin.auth_service.security.dto.JwtResponse;
import com.kiryukhin.auth_service.security.dto.RegistrationUserDto;
import com.kiryukhin.auth_service.security.dto.UserDto;
import com.kiryukhin.auth_service.security.jwt.JwtTokenUtils;
import com.kiryukhin.auth_service.securityEntities.User;
import com.kiryukhin.auth_service.system.exception.ApplicationErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(JwtRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            ));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new ApplicationErrorResponse(
                            HttpStatus.UNAUTHORIZED.value(),
                            "Incorrect login or password"),
                    HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new ApplicationErrorResponse(
                    HttpStatus.BAD_REQUEST.value(), "Password mismatch"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent() ||
                userService.findByEmail(registrationUserDto.getEmail()).isPresent()) {
            return new ResponseEntity<>(new ApplicationErrorResponse(
                    HttpStatus.BAD_REQUEST.value(), "There is already such a user"), HttpStatus.BAD_REQUEST);
        }
        List<String> rolesList = new ArrayList<>();
        rolesList.add("ROLE_USER");

        User user = userService.createNewUser(registrationUserDto, rolesList);
        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getEmail());
        return ResponseEntity.ok(userDto);
    }

}
