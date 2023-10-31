package com.kiryukhin.portfolio_service.system.exception.notFound;

public class PortfolioNotFoundException extends RuntimeException{
    public PortfolioNotFoundException(String message) {
        super(message);
    }
}
