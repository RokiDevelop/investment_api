package com.kiryukhin.auth_service.system.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationErrorResponse {
    private int status;
    private String message;
    private Date timestamp;

    public ApplicationErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
