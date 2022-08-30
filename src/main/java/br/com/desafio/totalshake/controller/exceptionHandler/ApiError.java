package br.com.desafio.totalshake.controller.exceptionHandler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    ApiError status(HttpStatus status){
        this.status =  status;
        return this;
    }

    ApiError message(String message){
        this.message =  message;
        return this;
    }

    ApiError debugMessage(String debugMessage){
        this.debugMessage =  debugMessage;
        return this;
    }

}
