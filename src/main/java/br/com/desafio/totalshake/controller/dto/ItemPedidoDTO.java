package br.com.desafio.totalshake.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemPedidoDTO {

    private Long id;

    @NotNull
    @Min(1)
    @JsonProperty("quantidade")
    private Integer quantidade;

    @NotNull(message = "Description can't be null!")
    @Size(min = 5, max = 15)
    @JsonProperty("descricao")
    @NotEmpty
    private String descricao;

    @JsonProperty(namespace = "pedido", access = JsonProperty.Access.WRITE_ONLY)
    private PedidoDTO pedidoDTO;

    private ItemPedidoDTO(Integer quantidade, String descricao, PedidoDTO pedidoDTO) {
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.pedidoDTO = pedidoDTO;
    }

    private ItemPedidoDTO(Integer quantidade, String descricao) {
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    private ItemPedidoDTO(Long id, Integer quantidade, String descricao) {
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.id = id;
    }


    public static ItemPedidoDTO of(Integer quantidade, String descricao, PedidoDTO pedidoDTO){
        return new ItemPedidoDTO(quantidade, descricao, pedidoDTO);
    }

    public static ItemPedidoDTO of(Long id, Integer quantidade, String descricao){
        return new ItemPedidoDTO(id, quantidade, descricao);
    }

    public static ItemPedidoDTO of(Integer quantidade, String descricao){
        return new ItemPedidoDTO(quantidade, descricao);
    }
}
