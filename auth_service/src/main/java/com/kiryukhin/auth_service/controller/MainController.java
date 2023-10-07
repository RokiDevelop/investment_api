package com.kiryukhin.auth_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "Unsecured data";
    }

    @GetMapping("/user")
    public String userPage(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Admin data";
    }
}
