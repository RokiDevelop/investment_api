package com.kiryukhin.portfolio_service.system.exception.serviceNotResponses;

import feign.FeignException;

public class ExternalServiceNotResponses extends FeignException {
    public ExternalServiceNotResponses(int integer, String message) {
        super(integer, message);
    }
}
