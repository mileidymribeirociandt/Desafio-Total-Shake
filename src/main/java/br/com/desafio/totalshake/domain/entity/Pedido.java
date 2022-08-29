package br.com.desafio.totalshake.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora")
    @NotNull
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "pedido",
            cascade = CascadeType.REMOVE
    )
    @NotNull
    private List<ItemPedido> itensPedidoList;

    private Pedido(LocalDateTime dataHora, Status status, List<ItemPedido> itensPedidoList) {
        this.dataHora = dataHora;
        this.status = status;
        this.itensPedidoList = itensPedidoList;
    }

    public static Pedido of(LocalDateTime dataHora, Status status, List<ItemPedido> itensPedidoList){
        return new Pedido(dataHora, status, itensPedidoList);
    }

    public static Pedido of(Long id, LocalDateTime dataHora, Status status, List<ItemPedido> itensPedidoList){
        return new Pedido(id, dataHora, status, itensPedidoList);
    }
}
