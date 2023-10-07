package com.kiryukhin.portfolio_service.security.services;

import com.kiryukhin.portfolio_service.security.dto.UserdataResponse;
import com.kiryukhin.portfolio_service.security.securityEntities.User;
import com.kiryukhin.portfolio_service.system.exception.ApplicationErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserdataResponseService {
    private final UserService userService;

    public ResponseEntity<?> getUserdataResponse(String username) {
        User user;
        try {
            user = userService.findUser(username);
        } catch (UsernameNotFoundException u) {
            return new ResponseEntity<>(
                    new ApplicationErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            "User not found!"),
                    HttpStatus.BAD_REQUEST);
        }

        UserdataResponse userdataResponse = new UserdataResponse();

        userdataResponse.setUsername(user.getUsername());
        userdataResponse.setEmail(user.getEmail());
        userdataResponse.setRoles(user.getRoles().toString());

        return ResponseEntity.ok(userdataResponse);
    }
}
