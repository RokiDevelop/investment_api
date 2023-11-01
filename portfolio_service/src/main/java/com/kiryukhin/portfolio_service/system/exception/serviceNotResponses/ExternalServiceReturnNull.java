package com.kiryukhin.portfolio_service.system.exception.serviceNotResponses;

public class ExternalServiceReturnNull extends RuntimeException {
    public ExternalServiceReturnNull(String message) {
        super(message);
    }
}
