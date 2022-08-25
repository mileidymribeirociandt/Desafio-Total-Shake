package br.com.desafio.totalshake.builder;

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
        itemPedido.of(1, "descricao do item", PedidoBuilder.getBuilder().validPedido());
        return this;
    }

    public ItemPedidoBuilder emptyItemPedido(){
        itemPedido.of();
        return this;
    }

    public ItemPedidoBuilder itemPedidoWithQuantityBelowZero(){
        itemPedido.of(-2, "descricao do item", PedidoBuilder.getBuilder().validPedido());
        return this;
    }

    public ItemPedidoBuilder itemPedidoWithQuantityEqualsZero(){
        itemPedido.of(0, "descricao do item", PedidoBuilder.getBuilder().validPedido());
        return this;
    }

    public ItemPedido build(){
        return itemPedido;
    }

    public List<ItemPedido> buildList(){
        return itensPedidos;
    }

    private void initList(){
        itensPedidos.add(itemPedido.of(1, "descricao do item", PedidoBuilder.getBuilder().validPedido()));
    }
}
