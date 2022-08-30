package br.com.desafio.totalshake.service.impl;

import br.com.desafio.totalshake.controller.dto.ItemPedidoDTO;
import br.com.desafio.totalshake.domain.entity.ItemPedido;
import br.com.desafio.totalshake.domain.entity.Pedido;
import br.com.desafio.totalshake.domain.repository.ItemPedidoRepository;
import br.com.desafio.totalshake.service.ItemPedidoService;
import br.com.desafio.totalshake.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static br.com.desafio.totalshake.service.impl.Util.*;
import static java.util.Objects.isNull;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public List<ItemPedidoDTO> findByPedidoId(Long pedidoId) {
        verifyId(pedidoId);
        return Mapper.toItemPedidoDTOList(itemPedidoRepository.findByPedidoId(pedidoId));
    }

    @Override
    public ItemPedidoDTO save(ItemPedidoDTO itemPedidoDTO, Pedido pedido) {
        verifyItemPedidoDTO(itemPedidoDTO);
        ItemPedido itemPedido = Mapper.toItemPedido(itemPedidoDTO, pedido);
        return Mapper.toItemPedidoDto(itemPedidoRepository.save(itemPedido));
    }

    @Override
    public List<ItemPedidoDTO> saveAll(List<ItemPedidoDTO> itemPedidoDTOList, Pedido pedido) {
        if(itemPedidoDTOList == null){
            throw new EmptyItemPedidoListException("List of ItemPedido can't bem null or empty!");
        }

        List<ItemPedidoDTO> returnedDTOList = new ArrayList<>(itemPedidoDTOList.size());

        itemPedidoDTOList.forEach(itemPedidoDTO -> {
            verifyItemPedidoDTO(itemPedidoDTO);
            returnedDTOList.add(save(itemPedidoDTO, pedido));
        });

        return returnedDTOList;
    }

    @Override
    @Transactional
    public List<ItemPedidoDTO> updateAll(List<ItemPedidoDTO> itensPedidoDTO, Long pedidoId) {
        itensPedidoDTO.forEach(itemPedidoDTO -> {
            verifyItemPedidoDTO(itemPedidoDTO);
            itemPedidoRepository.updateByPedidoId(itemPedidoDTO.getDescricao(), itemPedidoDTO.getQuantidade(), itemPedidoDTO.getId());
        });
        return Mapper.toItemPedidoDTOList(itemPedidoRepository.findByPedidoId(pedidoId));
    }

    private void verifyItemPedidoDTO(ItemPedidoDTO itemPedidoDTO){
        if(isNull(itemPedidoDTO)){
            throw new EmptyItemPedidoException("ItemPedido can't be null or empty");
        }

        if(isNull(itemPedidoDTO.getDescricao())){
            throw new NullPointerException("ItemPedido description can't be null");
        }

        if(itemPedidoDTO.getDescricao().isEmpty()){
            throw new InvalidIdException("ItemPedido description can't be empty");
        }

        if(isNegative(itemPedidoDTO.getQuantidade())){
            throw new InvalidQuantityException("ItemPedido quantity can't be negative");
        }

        if(isZero(itemPedidoDTO.getQuantidade())){
            throw new InvalidQuantityException("ItemPedido quantity can't be 0");
        }
    }

}
