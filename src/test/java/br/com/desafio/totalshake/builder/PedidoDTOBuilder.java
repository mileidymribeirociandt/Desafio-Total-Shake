package br.com.desafio.totalshake.builder;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTOBuilder {
    private PedidoDTO pedidoDTO;
    private static PedidoDTOBuilder builder = null;

    private PedidoDTOBuilder() {
    }

    public static PedidoDTOBuilder getBuilder() {
        if(builder == null){
            builder = new PedidoDTOBuilder();
        }
        return builder;
    }

    public PedidoDTOBuilder emptyPedido(){
        pedidoDTO.of();
        return this;
    }

    public PedidoDTOBuilder pedidoWithEmptyItemPedidoList(){
        pedidoDTO.of(LocalDateTime.now().minusDays(1), Status.REALIZADO, List.of());
        return this;
    }

    public PedidoDTOBuilder pedidoWithFutureDatetime(){
        pedidoDTO.of(LocalDateTime.now().plusDays(2), Status.REALIZADO, ItemPedidoDTOBuilder.getBuilder().buildList());
        return this;
    }

    public PedidoDTOBuilder validPedido(){
        pedidoDTO.of(LocalDateTime.now().minusDays(2), Status.REALIZADO, ItemPedidoDTOBuilder.getBuilder().buildList());
        return this;
    }

    public PedidoDTOBuilder pedidoToUpdateStatusRealizado(){
        pedidoDTO.of(LocalDateTime.now().minusDays(2), Status.REALIZADO, ItemPedidoDTOBuilder.getBuilder().buildList());
        return this;
    }

    public PedidoDTOBuilder pedidoUpdatedStatusEntregue(){
        pedidoDTO.of(LocalDateTime.now().minusDays(2), Status.REALIZADO, ItemPedidoDTOBuilder.getBuilder().buildList());
        return this;
    }

    public PedidoDTO build(){
        return pedidoDTO;
    }
}
