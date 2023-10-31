package com.kiryukhin.portfolio_service.system.exception.serviceNotResponses;

import feign.FeignException;

public class ExternalServiceReturnNull extends RuntimeException {
    public ExternalServiceReturnNull(String message) {
        super(message);
    }
}
