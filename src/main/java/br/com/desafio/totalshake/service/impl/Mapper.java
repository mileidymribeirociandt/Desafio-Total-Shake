package br.com.desafio.totalshake.service.impl;

import br.com.desafio.totalshake.controller.dto.ItemPedidoDTO;
import br.com.desafio.totalshake.controller.dto.PedidoDTO;
import br.com.desafio.totalshake.domain.entity.ItemPedido;
import br.com.desafio.totalshake.domain.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

final class Mapper {

    public static List<PedidoDTO> toPedidoDTOList(List<Pedido> pedidoList){
        return pedidoList.stream()
                .map(pedido -> toPedidoDTO(pedido))
                .collect(Collectors.toList());
    }

    public static Page<PedidoDTO> toPedidoDTOPage(Page<Pedido> pedidoPages){
        return new PageImpl<>(pedidoPages.stream()
                .map(pedido -> toPedidoDTO(pedido))
                .collect(Collectors.toList()));
    }

    public static Pedido toPedido (PedidoDTO pedidoDTO){
        return Pedido.of(
                pedidoDTO.getDataHora(),
                pedidoDTO.getStatus(),
                toItemPedidoList(pedidoDTO.getItensPedidoList())
        );
    }

    public static Pedido toPedido (PedidoDTO pedidoDTO, Long id){
        return Pedido.of(
                id,
                pedidoDTO.getDataHora(),
                pedidoDTO.getStatus(),
                toItemPedidoList(pedidoDTO.getItensPedidoList())
        );
    }

    public static PedidoDTO toPedidoDTO (Pedido pedido){
        return PedidoDTO.of(
                pedido.getDataHora(),
                pedido.getStatus(),
                toItemPedidoDTOList(pedido.getItensPedidoList())
        );
    }

    public static PedidoDTO toPedidoDTO (Pedido pedido, List<ItemPedidoDTO> itemPedidoDTOList){
        return PedidoDTO.of(
                pedido.getDataHora(),
                pedido.getStatus(),
                itemPedidoDTOList
        );
    }

    public static List<ItemPedidoDTO> toItemPedidoDTOList (List<ItemPedido> itemPedidoList){
        return itemPedidoList.stream()
                .map(itemPedido -> {
                    return ItemPedidoDTO.of(itemPedido.getId(), itemPedido.getQuantidade(), itemPedido.getDescricao());
                })
                .collect(Collectors.toList());
    }

    public static ItemPedidoDTO toItemPedidoDto (ItemPedido itemPedido){
        return ItemPedidoDTO.of(itemPedido.getQuantidade(), itemPedido.getDescricao());
    }

    public static ItemPedido toItemPedido (ItemPedidoDTO itemPedidoDTO, Pedido pedido){
        return ItemPedido.of(itemPedidoDTO.getQuantidade(), itemPedidoDTO.getDescricao(), pedido);
    }

    public static List<ItemPedido> toItemPedidoList (List<ItemPedidoDTO> itemPedidoDTOList){
        return itemPedidoDTOList.stream()
                .map(itemPedidoDTO -> {
                    return ItemPedido.of(itemPedidoDTO.getQuantidade(), itemPedidoDTO.getDescricao());
                })
                .collect(Collectors.toList());
    }
}
