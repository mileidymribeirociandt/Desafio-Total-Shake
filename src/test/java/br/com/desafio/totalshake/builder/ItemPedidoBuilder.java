package br.com.desafio.totalshake.builder;

import br.com.desafio.totalshake.domain.entity.ItemPedido;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoBuilder {
    private ItemPedido itemPedido;
    private List<ItemPedido> itensPedidos;
    private static ItemPedidoBuilder builder = null;

    private ItemPedidoBuilder() {
        this.initList();
    }

    public static ItemPedidoBuilder getBuilder() {
        if(builder == null){
            builder = new ItemPedidoBuilder();
        }
        return builder;
    }

    public ItemPedidoBuilder validItemPedido(){
        ItemPedido.of(1, "descricao do item", null);
        return this;
    }

    public ItemPedidoBuilder emptyItemPedido(){
        ItemPedido.of(null, null, null);
        return this;
    }

    public ItemPedidoBuilder itemPedidoWithQuantityBelowZero(){
        ItemPedido.of(-2, "descricao do item", null);
        return this;
    }

    public ItemPedidoBuilder itemPedidoWithQuantityEqualsZero(){
        ItemPedido.of(0, "descricao do item", null);
        return this;
    }

    public ItemPedido build(){
        return itemPedido;
    }

    public List<ItemPedido> buildList(){
        return itensPedidos;
    }

    private void initList(){
        itensPedidos = new ArrayList<>(1);
        itensPedidos.add(itemPedido.of(1, "descricao do item", PedidoBuilder.getBuilder().validPedidoToReturn().build()));
    }
}
