package com.kiryukhin.portfolio_service.security.dto;

import lombok.Data;

@Data
public class UserdataResponse {
    private String username;
    private String email;
    private String roles;
}
