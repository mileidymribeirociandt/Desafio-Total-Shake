package br.com.desafio.totalshake.service;

import br.com.desafio.totalshake.builder.ItemPedidoBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemPedidoServiceTest {

    @InjectMocks
    private ItemPedidoService itemPedidoService;

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    private static final int NEGATIVE_ID = -2;
    private static final int VALID_ID = 1;
    private static final int ZERO_ID = 0;
    private static final int NON_EXISTING_ID = Integer.MAX_VALUE;

    @BeforeAll
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("Save item pedido method scenarios test")
    class SaveItemPedidoTest{

        @Test
        void shouldThrowException_whenSavingEmptyItemPedido () {

            ItemPedido emptyItemPedido = ItemPedidoBuilder
                    .getBuilder()
                    .emptyItemPedido()
                    .build();

            Mockito.when(itemPedidoRepository.save(emptyItemPedido))
                    .thenThrow(new EmptyItemPedidoException());

            Executable executable = () -> itemPedidoService.save(emptyItemPedido);

            assertThrows(EmptyItemPedidoException.class, executable);
        }

        @Test
        void shouldThrowException_whenSavingItemPedidoWithQuantityEqualsZero () {

            ItemPedido itemPedidoWithQuantityEqualsZero = ItemPedidoBuilder
                    .getBuilder()
                    .itemPedidoWithQuantityEqualsZero()
                    .build();

            Mockito.when(itemPedidoRepository.save(itemPedidoWithQuantityEqualsZero))
                    .thenThrow(new InvalidQuantityException());

            Executable executable = () -> itemPedidoService.save(itemPedidoWithQuantityEqualsZero);

            assertThrows(InvalidQuantityException.class, executable);
        }

        @Test
        void shouldThrowException_whenSavingItemPedidoWithQuantityBelowZero () {

            ItemPedido itemPedidoWithQuantityBelowZero = ItemPedidoBuilder
                    .getBuilder()
                    .itemPedidoWithQuantityBelowZero()
                    .build();

            Mockito.when(itemPedidoRepository.save(itemPedidoWithQuantityBelowZero))
                    .thenThrow(new InvalidQuantityException());

            Executable executable = () -> itemPedidoService.save(itemPedidoWithQuantityBelowZero);

            assertThrows(InvalidQuantityException.class, executable);
        }

    }

    @Nested
    @DisplayName("Find item pedido method scenarios test")
    class FindItemPedidoTest{

        @Test
        void shouldThrowException_whenFindNonExistingItemPedido () {

            Mockito.when(itemPedidoRepository.findById(Mockito.anyInt()))
                    .thenThrow(new ItemPedidoNotFoundException());

            Executable executable = () -> itemPedidoService.findById(NON_EXISTING_ID);

            assertThrows(ItemPedidoNotFoundException.class, executable);
        }

        @Test
        void shouldThrowException_whenFindItemPedidoPassingIdEqualsZero () {

            Mockito.when(itemPedidoRepository.findById(Mockito.anyInt()))
                    .thenThrow(new InvalidIdException());

            Executable executable = () -> itemPedidoService.findById(ZERO_ID);

            assertThrows(InvalidIdException.class, executable);
        }

        @Test
        void shouldThrowException_whenFindItemPedidoPassingIdBelowZero () {

            Mockito.when(itemPedidoRepository.findById(Mockito.anyInt()))
                    .thenThrow(new InvalidIdException());

            Executable executable = () -> itemPedidoService.findById(NEGATIVE_ID);

            assertThrows(InvalidIdException.class, executable);
        }

        @Test
        void shouldThrowException_whenFindItemPedidoPassingIdPedidoEqualsZero () {

            Mockito.when(itemPedidoRepository.findByPedidoId(Mockito.anyInt()))
                    .thenThrow(new InvalidIdException());

            Executable executable = () -> itemPedidoService.findByPedidoId(ZERO_ID);

            assertThrows(InvalidIdException.class, executable);
        }

        @Test
        void shouldThrowException_whenFindItemPedidoPassingIdPedidoBelowZero () {

            Mockito.when(itemPedidoRepository.findByPedidoId(Mockito.anyInt()))
                    .thenThrow(new InvalidIdException());

            Executable executable = () -> itemPedidoService.findByPedidoId(NEGATIVE_ID);

            assertThrows(InvalidIdException.class, executable);
        }

    }

    @Nested
    @DisplayName("Update item pedido method scenarios test")
    class UpdateItemPedidoTest{

        @Test
        void shouldThrowException_whenUpdatingItemPedidoWithQuantityEqualsZero () {

            ItemPedido itemPedido = ItemPedidoBuilder
                    .getBuilder()
                    .itemPedidoWithQuantityEqualsZero()
                    .build();

            Mockito.when(itemPedidoRepository.findByPedidoId(Mockito.anyInt()))
                    .thenThrow(new InvalidQuantityException());

            Executable executable = () -> itemPedidoService.update(itemPedido);

            assertThrows(InvalidQuantityException.class, executable);

        }

        @Test
        void shouldThrowException_whenUpdatingItemPedidoWithQuantityBelowZero () {

            ItemPedido itemPedido = ItemPedidoBuilder
                    .getBuilder()
                    .itemPedidoWithQuantityBelowZero()
                    .build();

            Mockito.when(itemPedidoRepository.findByPedidoId(Mockito.anyInt()))
                    .thenThrow(new InvalidQuantityException());

            Executable executable = () -> itemPedidoService.update(itemPedido);

            assertThrows(InvalidQuantityException.class, executable);
        }

    }

    @Nested
    @DisplayName("Delete pedido methods scenarios test")
    class DeletePedidoTest{
        @Test
        void shouldThrowException_whenDeletingANonExistingItemPedidoById(){

            Mockito.when(itemPedidoRepository.deleteById(Mockito.anyInt()))
                    .thenThrow(new ItemPedidoNotFoundException());

            Executable executable = () -> itemPedidoService.deleteById(NON_EXISTING_ID);

            assertThrows(ItemPedidoNotFoundException.class, executable);
        }

        @Test
        void shouldThrowException_whenDeletingAnItemPedidoPedidoPassingNegativeId(){

            Mockito.when(itemPedidoRepository.deleteById(Mockito.anyInt()))
                    .thenThrow(new InvalidIdException());

            Executable executable = () -> itemPedidoService.deleteById(NEGATIVE_ID);

            assertThrows(InvalidIdException.class, executable);
        }

        @Test
        void shouldThrowException_whenDeletingAnItemPedidoPassingIdEqualsZero(){

            Mockito.when(itemPedidoRepository.deleteById(Mockito.anyInt()))
                    .thenThrow(new InvalidIdException());

            Executable executable = () -> itemPedidoService.deleteById(ZERO_ID);

            assertThrows(InvalidIdException.class, executable);
        }

    }
}
