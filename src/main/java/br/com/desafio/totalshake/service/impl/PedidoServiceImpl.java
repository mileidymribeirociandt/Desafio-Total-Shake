package br.com.desafio.totalshake.service.impl;

import br.com.desafio.totalshake.controller.dto.ItemPedidoDTO;
import br.com.desafio.totalshake.controller.dto.PedidoDTO;
import br.com.desafio.totalshake.domain.entity.Pedido;
import br.com.desafio.totalshake.domain.repository.PedidoRepository;
import br.com.desafio.totalshake.service.ItemPedidoService;
import br.com.desafio.totalshake.service.PedidoService;
import br.com.desafio.totalshake.service.exceptions.EmptyItemPedidoListException;
import br.com.desafio.totalshake.service.exceptions.EmptyPedidoException;
import br.com.desafio.totalshake.service.exceptions.FutureDateTimeException;
import br.com.desafio.totalshake.service.exceptions.PedidoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.desafio.totalshake.service.impl.Util.*;
import static java.util.Objects.isNull;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PedidoDTO save(PedidoDTO pedidoDTO) {
        verifyPedidoDTO(pedidoDTO);
        Pedido pedido = pedidoRepository.save(Mapper.toPedido(pedidoDTO));
        List<ItemPedidoDTO> savedItemPedidoDTOList = itemPedidoService.saveAll(pedidoDTO.getItensPedidoList(), pedido);
        return Mapper.toPedidoDTO(pedido, savedItemPedidoDTOList);
    }

    @Override
    @Transactional
    public PedidoDTO update(PedidoDTO pedidoDTO, Long id) {
        verifyPedidoDTO(pedidoDTO);
        Pedido updatedPedido = pedidoRepository.save(Mapper.toPedido(pedidoDTO, id));
        List<ItemPedidoDTO> updatedItemPedidoDTOList = itemPedidoService.updateAll(pedidoDTO.getItensPedidoList(), id);
        return Mapper.toPedidoDTO(updatedPedido, updatedItemPedidoDTOList);
    }

    @Override
    public PedidoDTO findById(Long id){
        verifyId(id);
        Optional<Pedido> optionalPedido = pedidoRepository.findById(id);
        if(optionalPedido.isPresent()){
            return Mapper.toPedidoDTO(optionalPedido.get());
        }
        throw new PedidoNotFoundException("Pedido not found!");
    }

    @Override
    public List<PedidoDTO> findAll() {
        return Mapper.toPedidoDTOList(pedidoRepository.findAll());
    }

    private void verifyPedidoDTO(PedidoDTO pedidoDTO){
        if(isNull(pedidoDTO)){
            throw new EmptyPedidoException("PedidoDTO can't be null or empty");
        }

        if(isDateTimeInFuture(pedidoDTO.getDataHora())){
            throw new FutureDateTimeException("Datetime can't be in future!");
        }

        if(isEmptyOrNullItemList(pedidoDTO.getItensPedidoList())){
            throw new EmptyItemPedidoListException("Item pedido list can't be empty or null");
        }
    }

}
