package br.com.desafio.totalshake.service.exceptions;

public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException() {
    }

    public PedidoNotFoundException(String message) {
        super(message);
    }
}
