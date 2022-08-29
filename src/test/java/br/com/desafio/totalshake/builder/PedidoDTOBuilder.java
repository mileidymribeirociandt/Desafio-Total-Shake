package br.com.desafio.totalshake.builder;

import br.com.desafio.totalshake.controller.dto.ItemPedidoDTO;
import br.com.desafio.totalshake.controller.dto.PedidoDTO;
import br.com.desafio.totalshake.domain.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTOBuilder {

    private LocalDateTime baseLocalDateTime;
    private final LocalDate baseDate = LocalDate.now().minusDays(2);
    private final LocalTime baseTime = LocalTime.of(00, 00, 00, 000);
    private PedidoDTO pedidoDTO;
    private static PedidoDTOBuilder builder = null;
    private List<PedidoDTO> pedidos;
    private List<ItemPedidoDTO> itemPedidoDTOList;

    private PedidoDTOBuilder() {
        this.baseLocalDateTime = LocalDateTime.of(baseDate, baseTime);
        this.initPedidoDTOList();
    }

    public static PedidoDTOBuilder getBuilder() {
        if(builder == null){
            builder = new PedidoDTOBuilder();
        }
        return builder;
    }

    public PedidoDTOBuilder emptyPedido(){
        pedidoDTO = null;
        return this;
    }

    public PedidoDTOBuilder pedidoWithEmptyItemPedidoList(){
        pedidoDTO = PedidoDTO.of(LocalDateTime.now().minusDays(1), Status.REALIZADO, List.of());
        return this;
    }

    public PedidoDTOBuilder pedidoWithFutureDatetime(){
        pedidoDTO = PedidoDTO.of(LocalDateTime.now().plusDays(2), Status.REALIZADO, List.of());
        return this;
    }

    public PedidoDTOBuilder validPedidoToReturn(){
        initValidItemPedidoListToWithPedido();
        pedidoDTO = PedidoDTO.of(baseLocalDateTime, Status.REALIZADO, itemPedidoDTOList);
        return this;
    }

    public PedidoDTO build(){
        return pedidoDTO;
    }

    public List<PedidoDTO> buildList(){
        return pedidos;
    }

    private void initPedidoDTOList(){
        pedidos = new ArrayList<>(3);
        pedidos.add(PedidoDTO.of(LocalDateTime.of(baseDate, baseTime), Status.REALIZADO, List.of()));
        pedidos.add(PedidoDTO.of(LocalDateTime.of(baseDate, baseTime), Status.ENTREGUE, List.of()));
        pedidos.add(PedidoDTO.of(LocalDateTime.of(baseDate, baseTime), Status.CANCELADO, List.of()));
    }

    public LocalDateTime getBaseLocalDateTime() {
        return baseLocalDateTime;
    }

    private void initValidItemPedidoListToWithPedido(){
        itemPedidoDTOList = new ArrayList<>(1);
        itemPedidoDTOList.add(ItemPedidoDTO.of(1, "descricao do item"));
    }
}
