package br.com.desafio.totalshake.builder;

import br.com.desafio.totalshake.domain.entity.ItemPedido;
import br.com.desafio.totalshake.domain.entity.Pedido;
import br.com.desafio.totalshake.domain.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder {
    private LocalDateTime baseLocalDateTime;
    private final LocalDate baseDate = LocalDate.now().minusDays(2);
    private final LocalTime baseTime = LocalTime.of(00, 00, 00, 000);
    private List<Pedido> pedidos;
    private Pedido pedido;

    private List<ItemPedido> itemPedidoList;
    private static PedidoBuilder builder = null;

    private PedidoBuilder() {
        this.baseLocalDateTime = LocalDateTime.of(baseDate, baseTime);
        this.initPedidoList();
    }

    public static PedidoBuilder getBuilder() {
        if(builder == null){
            builder = new PedidoBuilder();
        }
        return builder;
    }

    public PedidoBuilder validPedidoToReturn(){
        initValidItemPedidoListToWithPedido();
        pedido = Pedido.of(baseLocalDateTime, Status.REALIZADO, itemPedidoList);
        return this;
    }

    public PedidoBuilder validPedidoToSave(){
        initValidItemPedidoListToWithPedido();
        pedido = Pedido.of(baseLocalDateTime, Status.REALIZADO, itemPedidoList);
        return this;
    }

    public Pedido build(){
        return pedido;
    }

    public List<Pedido> buildList(){
        return pedidos;
    }

    private void initPedidoList(){
        pedidos = new ArrayList<>(3);
        pedidos.add(Pedido.of(LocalDateTime.of(baseDate, baseTime), Status.REALIZADO, List.of()));
        pedidos.add(Pedido.of(LocalDateTime.of(baseDate, baseTime), Status.ENTREGUE, List.of()));
        pedidos.add(Pedido.of(LocalDateTime.of(baseDate, baseTime), Status.CANCELADO, List.of()));
    }

    private void initValidItemPedidoListToWithPedido(){
        itemPedidoList = new ArrayList<>(1);
        itemPedidoList.add(ItemPedido.of(1, "descricao do item", Pedido.of(baseLocalDateTime, Status.REALIZADO, itemPedidoList)));
    }

}
