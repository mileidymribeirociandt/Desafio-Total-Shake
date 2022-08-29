package br.com.desafio.totalshake.service.exceptions;

public class InvalidItemPedidoException extends RuntimeException{
    public InvalidItemPedidoException() {
    }

    public InvalidItemPedidoException(String message) {
        super(message);
    }
}
