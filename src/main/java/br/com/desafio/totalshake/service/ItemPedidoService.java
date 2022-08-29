package br.com.desafio.totalshake.service;

import br.com.desafio.totalshake.controller.dto.ItemPedidoDTO;
import br.com.desafio.totalshake.domain.entity.Pedido;
import br.com.desafio.totalshake.service.exceptions.ItemPedidoNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemPedidoService {
    List<ItemPedidoDTO> findByPedidoId(Long pedidoId);

    ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO, Pedido pedido);

    @Transactional
    List<ItemPedidoDTO> saveAll(List<ItemPedidoDTO> itemPedidoDTOList, Pedido pedido);

    List<ItemPedidoDTO> updateAll(List<ItemPedidoDTO> itensPedidoDTO, Long id);
}
