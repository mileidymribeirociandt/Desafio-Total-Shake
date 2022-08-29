package br.com.desafio.totalshake.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "item_pedido")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull(message = "Description can't be null!")
    @Size(min = 5, max = 15)
    private String descricao;

    @ManyToOne
    @JoinColumn(name="pedido_id", referencedColumnName = "id", nullable=false)
    private Pedido pedido;

    private ItemPedido(Integer quantidade, String descricao, Pedido pedido) {
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.pedido = pedido;
    }

    public static ItemPedido of(Integer quantidade, String descricao, Pedido pedido){
        return new ItemPedido(quantidade, descricao, pedido);
    }

    public static ItemPedido of(Integer quantidade, String descricao){
        return new ItemPedido(quantidade, descricao, null);
    }
}
