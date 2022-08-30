package br.com.desafio.totalshake.service.exceptions;

public class FutureDateTimeException extends RuntimeException{
    public FutureDateTimeException() {
    }

    public FutureDateTimeException(String message) {
        super(message);
    }
}
