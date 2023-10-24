package com.kiryukhin.portfolio_service.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({PortfolioNotFoundException.class, StockNotFoundException.class})
    public ResponseEntity<ErrorMessageDto> handleNotFound(Exception ex) {
        return new ResponseEntity<ErrorMessageDto>(new ErrorMessageDto(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }
}
