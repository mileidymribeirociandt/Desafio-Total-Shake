package br.com.desafio.totalshake.builder;

import br.com.desafio.totalshake.controller.dto.ItemPedidoDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDTOBuilder {
    private ItemPedidoDTO itemPedidoDTO;

    private List<ItemPedidoDTO> itensPedidosDTO;
    private static ItemPedidoDTOBuilder builder = null;

    private ItemPedidoDTOBuilder() {
        this.initList();
    }

    public static ItemPedidoDTOBuilder getBuilder() {
        if(builder == null){
            builder = new ItemPedidoDTOBuilder();
        }
        return builder;
    }

    public ItemPedidoDTO build(){
        return itemPedidoDTO;
    }

    public List<ItemPedidoDTO> buildList(){
        return itensPedidosDTO;
    }

    public ItemPedidoDTOBuilder emptyItemPedido(){
        itemPedidoDTO = null;
        return this;
    }

    public ItemPedidoDTOBuilder itemPedidoWithQuantityBelowZero(){
        itemPedidoDTO = ItemPedidoDTO.of(-2, "descricao do item", null);
        return this;
    }

    public ItemPedidoDTOBuilder itemPedidoWithQuantityEqualsZero(){
        itemPedidoDTO = ItemPedidoDTO.of(0, "descricao do item", null);
        return this;
    }

    private void initList(){
        itensPedidosDTO = new ArrayList<>(1);
        itensPedidosDTO.add(itemPedidoDTO.of(1, "descricao do item", null));
    }
}
