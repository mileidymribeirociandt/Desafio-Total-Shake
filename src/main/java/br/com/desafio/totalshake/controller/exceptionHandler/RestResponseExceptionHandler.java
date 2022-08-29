package br.com.desafio.totalshake.controller.exceptionHandler;

import br.com.desafio.totalshake.service.exceptions.PedidoNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PedidoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handlePedidoNotFoundException(Throwable throwable){
        ApiError apiError = new ApiError();
        apiError.debugMessage(throwable.getMessage())
                .message(throwable.getMessage())
                .status(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}
