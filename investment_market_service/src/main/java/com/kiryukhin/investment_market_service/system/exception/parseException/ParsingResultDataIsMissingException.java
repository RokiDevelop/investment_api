package com.kiryukhin.investment_market_service.system.exception.parseException;

public class ParsingResultDataIsMissingException extends RuntimeException{
    public ParsingResultDataIsMissingException(String message) {
        super(message);
    }
    public ParsingResultDataIsMissingException() {
        super();
    }
}