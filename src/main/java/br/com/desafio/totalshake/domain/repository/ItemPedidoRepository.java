package br.com.desafio.totalshake.domain.repository;

import br.com.desafio.totalshake.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findByPedidoId(Long pedidoId);

    @Query(value = "UPDATE item_pedido SET descricao = ?1, quantidade = ?2 WHERE id = ?3 ", nativeQuery = true)
    @Modifying
    void updateByPedidoId(String descricao, Integer quantidade, Long id);
}
