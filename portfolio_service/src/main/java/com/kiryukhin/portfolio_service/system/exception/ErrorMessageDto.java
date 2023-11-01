package com.kiryukhin.portfolio_service.system.exception;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ErrorMessageDto {
    private String message;
}
