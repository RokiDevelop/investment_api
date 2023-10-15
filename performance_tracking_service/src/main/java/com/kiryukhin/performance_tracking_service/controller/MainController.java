package com.kiryukhin.performance_tracking_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    public ResponseEntity<?> getMain(){
        return ResponseEntity.ok("Performance Tracking Service info");
    }
}
