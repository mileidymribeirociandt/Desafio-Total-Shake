package br.com.desafio.totalshake.service.exceptions;

public class EmptyPedidoException extends RuntimeException{
    public EmptyPedidoException() {
    }

    public EmptyPedidoException(String message) {
        super(message);
    }
}
