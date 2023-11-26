package com.kiryukhin.investment_market_service.system.exception;

import com.kiryukhin.investment_market_service.system.exception.parseException.ParsingResultDataIsMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            ParsingResultDataIsMissingException.class
    })
    public ResponseEntity<ErrorMessageDto> handleNotFound(Exception ex) {
        return new ResponseEntity<>(new ErrorMessageDto(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }
}
