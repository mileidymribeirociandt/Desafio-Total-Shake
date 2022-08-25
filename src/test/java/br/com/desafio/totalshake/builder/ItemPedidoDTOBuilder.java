package br.com.desafio.totalshake.builder;

public class ItemPedidoDTOBuilder {
    private ItemPedidoDTO itemPedido;

    private List<ItemPedidoDTO> itensPedidos;
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

    public List<ItemPedido> buildList(){
        return itensPedidos;
    }

    private void initList(){
        itensPedidos.add(itemPedido.of(1, "descricao do item", PedidoBuilder.getBuilder().validPedido()));
    }
}
