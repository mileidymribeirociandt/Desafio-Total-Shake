package br.com.desafio.totalshake.service.exceptions;

public class ItemPedidoNotFoundException extends Exception{
    public ItemPedidoNotFoundException() {
    }

    public ItemPedidoNotFoundException(String message) {
        super(message);
    }
}
