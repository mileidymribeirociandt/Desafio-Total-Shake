package br.com.desafio.totalshake.service;

import br.com.desafio.totalshake.builder.ItemPedidoDTOBuilder;
import br.com.desafio.totalshake.builder.PedidoBuilder;
import br.com.desafio.totalshake.controller.dto.ItemPedidoDTO;
import br.com.desafio.totalshake.domain.entity.ItemPedido;
import br.com.desafio.totalshake.domain.entity.Pedido;
import br.com.desafio.totalshake.domain.repository.ItemPedidoRepository;
import br.com.desafio.totalshake.builder.ItemPedidoBuilder;
import br.com.desafio.totalshake.service.impl.ItemPedidoServiceImpl;
import br.com.desafio.totalshake.service.exceptions.EmptyItemPedidoException;
import br.com.desafio.totalshake.service.exceptions.InvalidIdException;
import br.com.desafio.totalshake.service.exceptions.InvalidQuantityException;
import br.com.desafio.totalshake.service.exceptions.ItemPedidoNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemPedidoServiceTest {

    @InjectMocks
    private ItemPedidoServiceImpl itemPedidoService;

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    private static final long NEGATIVE_ID = -2;
    private static final long ZERO_ID = 0;

    private static final long VALID_ID = 0;
    private static final long NON_EXISTING_ID = Integer.MAX_VALUE;

    @BeforeAll
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("Save item pedido method scenarios test")
    class SaveItemPedidoTestDTO {

        @Test
        void shouldThrowException_whenSavingEmptyItemPedido () {

            Pedido pedidoToSave = PedidoBuilder
                    .getBuilder()
                    .validPedidoToSave()
                    .build();

            ItemPedidoDTO emptyItemPedidoDTO = ItemPedidoDTOBuilder
                    .getBuilder()
                    .emptyItemPedido()
                    .build();

            Executable executable = () -> itemPedidoService.save(emptyItemPedidoDTO, pedidoToSave);

            assertThrows(EmptyItemPedidoException.class, executable);
        }

        @Test
        void shouldThrowException_whenSavingItemPedidoWithQuantityEqualsZero () {

            Pedido pedidoToSave = PedidoBuilder
                    .getBuilder()
                    .validPedidoToSave()
                    .build();

            ItemPedidoDTO itemPedidoDTOWithQuantityEqualsZero = ItemPedidoDTOBuilder
                    .getBuilder()
                    .itemPedidoWithQuantityEqualsZero()
                    .build();

            Executable executable = () -> itemPedidoService.save(itemPedidoDTOWithQuantityEqualsZero, pedidoToSave);

            assertThrows(InvalidQuantityException.class, executable);
        }

        @Test
        void shouldThrowException_whenSavingItemPedidoWithQuantityBelowZero () {

            Pedido pedidoToSave= PedidoBuilder
                    .getBuilder()
                    .validPedidoToSave()
                    .build();

            ItemPedidoDTO itemPedidoDTOWithQuantityEqualsZero = ItemPedidoDTOBuilder
                    .getBuilder()
                    .itemPedidoWithQuantityEqualsZero()
                    .build();

            Executable executable = () -> itemPedidoService.save(itemPedidoDTOWithQuantityEqualsZero, pedidoToSave);

            assertThrows(InvalidQuantityException.class, executable);
        }

    }

    @Nested
    @DisplayName("Find item pedido method scenarios test")
    class FindItemPedidoTestDTO {

        @Test
        void shouldThrowException_whenFindItemPedidoPassingIdPedidoEqualsZero () {

            Executable executable = () -> itemPedidoService.findByPedidoId(ZERO_ID);

            assertThrows(InvalidIdException.class, executable);
        }

        @Test
        void shouldThrowException_whenFindItemPedidoPassingIdPedidoBelowZero () {

            Executable executable = () -> itemPedidoService.findByPedidoId(NEGATIVE_ID);

            assertThrows(InvalidIdException.class, executable);
        }

    }

    @Nested
    @DisplayName("Update item pedido method scenarios test")
    class UpdateItemPedidoTestDTO {

        @Test
        void shouldThrowException_whenUpdatingItemPedidoWithQuantityEqualsZero () {

            ItemPedidoDTO itemPedidoDTO = ItemPedidoDTOBuilder
                    .getBuilder()
                    .itemPedidoWithQuantityEqualsZero()
                    .build();


            Mockito.when(itemPedidoRepository.findByPedidoId(Mockito.anyLong()))
                    .thenThrow(new InvalidQuantityException());

            Executable executable = () -> itemPedidoService.updateAll(List.of(itemPedidoDTO), VALID_ID);

            assertThrows(InvalidQuantityException.class, executable);

        }

        @Test
        void shouldThrowException_whenUpdatingItemPedidoWithQuantityBelowZero () {

            ItemPedidoDTO itemPedidoDTO = ItemPedidoDTOBuilder
                    .getBuilder()
                    .itemPedidoWithQuantityBelowZero()
                    .build();

            Executable executable = () -> itemPedidoService.updateAll(List.of(itemPedidoDTO), VALID_ID);

            assertThrows(InvalidQuantityException.class, executable);
        }

    }
}
