package br.com.desafio.totalshake.service.exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException(String message) {
        super(message);
    }

    public InvalidQuantityException() {
    }
}
