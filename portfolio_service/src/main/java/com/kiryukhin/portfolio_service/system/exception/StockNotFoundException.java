package com.kiryukhin.portfolio_service.system.exception;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String message) {
        super(message);
    }
}
