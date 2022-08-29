package br.com.desafio.totalshake.service;

import br.com.desafio.totalshake.controller.dto.PedidoDTO;
import br.com.desafio.totalshake.service.exceptions.PedidoNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface PedidoService{

    void deleteById(Long id);

    PedidoDTO save(PedidoDTO pedidoDTO);

    @Transactional
    PedidoDTO update(PedidoDTO pedidoDTO, Long id);

    PedidoDTO findById(Long id);

    List<PedidoDTO> findAll();

}
