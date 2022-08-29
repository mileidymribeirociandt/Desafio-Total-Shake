package br.com.desafio.totalshake.service.exceptions;

public class EmptyItemPedidoException extends RuntimeException {
    public EmptyItemPedidoException() {
    }

    public EmptyItemPedidoException(String message) {
        super(message);
    }
}
