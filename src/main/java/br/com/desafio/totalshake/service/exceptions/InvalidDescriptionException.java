package br.com.desafio.totalshake.service.exceptions;

public class InvalidDescriptionException extends RuntimeException{
    public InvalidDescriptionException() {
    }

    public InvalidDescriptionException(String message) {
        super(message);
    }
}
