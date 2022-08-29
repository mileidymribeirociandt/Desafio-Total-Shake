package br.com.desafio.totalshake.service.exceptions;

public class EmptyItemPedidoListException extends RuntimeException{
    public EmptyItemPedidoListException() {
    }

    public EmptyItemPedidoListException(String message) {
        super(message);
    }
}
