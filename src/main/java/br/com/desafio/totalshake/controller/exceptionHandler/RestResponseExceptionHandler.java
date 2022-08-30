package br.com.desafio.totalshake.controller.exceptionHandler;

import br.com.desafio.totalshake.service.exceptions.PedidoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestResponseExceptionHandler {

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<ApiError> handlePedidoNotFoundException(Throwable throwable, WebRequest webRequest){
        ApiError apiError = new ApiError();
        apiError.debugMessage(throwable.getMessage())
                .message(throwable.getMessage())
                .status(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

}
