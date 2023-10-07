package com.kiryukhin.portfolio_service.controllers;

import com.kiryukhin.portfolio_service.security.services.UserdataResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolio")
public class MainControllers {
    private final UserdataResponseService userdataResponseService;


    @GetMapping("/main")
    public ResponseEntity<?> pagePortfolio(Principal principal) {
        return userdataResponseService.getUserdataResponse(principal.getName());
    }
}
