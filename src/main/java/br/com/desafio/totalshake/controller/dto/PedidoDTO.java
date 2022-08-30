package br.com.desafio.totalshake.controller.dto;

import br.com.desafio.totalshake.controller.annotations.DateTimeValidator;
import br.com.desafio.totalshake.domain.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoDTO {

    @DateTimeValidator
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("data_hora")
//    @JsonFormat
    private LocalDateTime dataHora;

    @NotNull
    private Status status;

    @NotNull
    @JsonProperty("itens_pedidos_list")
    private List<ItemPedidoDTO> itensPedidoList;


    private PedidoDTO(LocalDateTime dataHora, Status status, List<ItemPedidoDTO> itensPedidoList) {
        this.dataHora = dataHora;
        this.status = status;
        this.itensPedidoList = itensPedidoList;
    }

    public static PedidoDTO of(LocalDateTime dataHora, Status status, List<ItemPedidoDTO> itensPedidoList){
        return new PedidoDTO(dataHora, status, itensPedidoList);
    }

    public static PedidoDTO of(Status status, List<ItemPedidoDTO> itensPedidoList){
        return new PedidoDTO(LocalDateTime.now(), status, itensPedidoList);
    }
}
