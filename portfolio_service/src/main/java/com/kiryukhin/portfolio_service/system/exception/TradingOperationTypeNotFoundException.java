package com.kiryukhin.portfolio_service.system.exception;

public class TradingOperationTypeNotFoundException extends RuntimeException {
    public TradingOperationTypeNotFoundException(String message) {
        super(message);
    }
}
