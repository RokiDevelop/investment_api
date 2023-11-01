package com.kiryukhin.portfolio_service.system.exception;

import com.kiryukhin.portfolio_service.system.exception.notFound.AssetStockNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.notFound.PortfolioNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.notFound.StockNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.notFound.TradingOperationTypeNotFoundException;
import com.kiryukhin.portfolio_service.system.exception.serviceNotResponses.ExternalServiceNotResponses;
import com.kiryukhin.portfolio_service.system.exception.serviceNotResponses.ExternalServiceReturnNull;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            PortfolioNotFoundException.class,
            StockNotFoundException.class,
            AssetStockNotFoundException.class,
            TradingOperationTypeNotFoundException.class
    })
    public ResponseEntity<ErrorMessageDto> handleNotFound(Exception ex) {
        return new ResponseEntity<>(new ErrorMessageDto(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            ExternalServiceNotResponses.class
    })
    public ResponseEntity<ErrorMessageDto> handleExternalServiceNotResponsesException(Exception ex) {
        return new ResponseEntity<>(new ErrorMessageDto(ex.getLocalizedMessage()), HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler({
            ExternalServiceReturnNull.class
    })
    public ResponseEntity<ErrorMessageDto> handleExternalServiceReturnNull(Exception ex) {
        return new ResponseEntity<>(new ErrorMessageDto(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            UsernameNotFoundException.class
    })
    public ResponseEntity<ErrorMessageDto> handleUsernameNotFoundException(Exception ex) {
        return new ResponseEntity<>(new ErrorMessageDto(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            JwtException.class
    })
    public ResponseEntity<ErrorMessageDto> handleJwtException(Exception ex) {
        return new ResponseEntity<>(new ErrorMessageDto(ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
    }
}
