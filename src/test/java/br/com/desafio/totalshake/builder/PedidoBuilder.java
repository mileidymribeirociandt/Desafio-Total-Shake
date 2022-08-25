package br.com.desafio.totalshake.builder;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoBuilder {
    private Pedido pedido;
    private List<Pedido> pedidos;
    private static PedidoBuilder builder = null;

    private PedidoBuilder() {
        this.initPedidoList();
    }

    public static PedidoBuilder getBuilder() {
        if(builder == null){
            builder = new PedidoBuilder();
        }
        return builder;
    }

    public PedidoBuilder emptyPedido(){
        pedido.of();
        return this;
    }

    public PedidoBuilder pedidoWithEmptyItemPedidoList(){
        pedido.of(LocalDateTime.now().minusDays(1), Status.REALIZADO, List.of());
        return this;
    }

    public PedidoBuilder pedidoWithFutureDatetime(){
        pedido.of(LocalDateTime.now().plusDays(2), Status.REALIZADO, ItemPedidoBuilder.getBuilder().buildList());
        return this;
    }

    public PedidoBuilder validPedido(){
        pedido.of(LocalDateTime.now().minusDays(2), Status.REALIZADO, ItemPedidoBuilder.getBuilder().buildList());
        return this;
    }

    public PedidoBuilder pedidoToUpdateStatusRealizado(){
        pedido.of(LocalDateTime.now().minusDays(2), Status.REALIZADO, ItemPedidoBuilder.getBuilder().buildList());
        return this;
    }

    public PedidoBuilder pedidoUpdatedStatusEntregue(){
        pedido.of(LocalDateTime.now().minusDays(2), Status.REALIZADO, ItemPedidoBuilder.getBuilder().buildList());
        return this;
    }

    public Pedido build(){
        return pedido;
    }

    public List<Pedido> buildList(){
        return pedidos;
    }

    private void initPedidoList(){
        pedidos = new ArrayList<Pedido>(3);
        pedidos.add(pedido.of(LocalDateTime.now().minusDays(2), Status.REALIZADO, List.of(ItemPedidoBuilder.getBuilder().buildList())));
        pedidos.add(pedido.of(LocalDateTime.now().minusDays(2), Status.ENTREGUE, List.of(ItemPedidoBuilder.getBuilder().buildList())));
        pedidos.add(pedido.of(LocalDateTime.now().minusDays(2), Status.CANCELADO, List.of(ItemPedidoBuilder.getBuilder().buildList())));
    }

}
