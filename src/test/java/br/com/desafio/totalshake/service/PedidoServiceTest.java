package br.com.desafio.totalshake.service;

import br.com.desafio.totalshake.builder.PedidoBuilder;
import br.com.desafio.totalshake.builder.PedidoDTOBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    private static final int NEGATIVE_ID = -2;
    private static final int VALID_ID = 1;
    private static final int ZERO_ID = 0;
    private static final int NON_EXISTING_ID = Integer.MAX_VALUE;

    @BeforeAll
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("Save pedido method scenarios test")
    class SavePedidoTest{

        @Test
        void shouldThrowException_whenSavingEmptyPedido(){

            PedidoDTO pedidoDTO = PedidoDTOBuilder
                    .getBuilder()
                    .emptyPedido()
                    .build();

            when(pedidoRepository.save())
                    .thenThrow(new EmptyPedidoException());

            Executable executable = () -> pedidoService.save(pedidoDTO);

            assertThrows(EmptyPedidoException.class, executable);
        }

        @Test
        void shouldThrowException_whenSavingPedidoWithEmptyListOfItemPedido(){

            PedidoDTO pedidoDTO = PedidoBuilder
                    .getBuilder()
                    .pedidoWithEmptyItemPedidoList()
                    .build();

            when(pedidoRepository.save())
                    .thenThrow(new InvalidItemPedidoException());

            Executable executable = () -> pedidoService.save(pedidoDTO);

            assertThrows(InvalidItemPedidoException.class, executable);
        }

        @Test
        void shouldThrowException_whenSavingPedidoWithDateTimeInFuture(){

            PedidoDTO pedidoDTO = PedidoBuilder
                    .getBuilder()
                    .pedidoWithFutureDatetime()
                    .build();

            when(pedidoRepository.save())
                    .thenThrow(new CreationDatetimeException());

            Executable executable = () -> pedidoService.save(pedidoDTO);

            assertThrows(CreationDatetimeException.class, executable);
        }

    }

    @Nested
    @DisplayName("Find pedido methods scenarios test")
    class FindPedidoTest{

        @Test
        void shouldThrowException_whenFindingListOfPedidosWithDatetimeInFuture(){

            LocalDateTime datetime = LocalDateTime.now().plusDays(15);

            Executable executable = () -> pedidoService.findAllByDataHora(datetime);

            assertThrows(FutureDatetimException.class, executable);
        }

        @Test
        void shouldThrowException_whenFindingPedidoWithNegativeId(){
            Executable executable = () -> pedidoService.findById(NEGATIVE_ID);

            assertThrows(InvalidIdException.class, executable);
        }

        @Test
        void shouldThrowException_whenFindingPedidoWithZeroId(){
            Executable executable = () -> pedidoService.findById(ZERO_ID);

            assertThrows(InvalidIdException.class, executable);
        }

        @Test
        void shouldThrowException_whenFindingANonExistingPedido(){
            Executable executable = () -> pedidoService.findById(NON_EXISTING_ID);

            assertThrows(PedidoNotFoundException.class, executable);
        }

        @Test
        void shouldFindPedido_whenIdIsValid(){

            Pedido expectedPedido = PedidoBuilder
                    .getBuilder()
                    .validPedido()
                    .build();

            Mockito.when(pedidoRepository.findById(Mockito.anyInt()))
                    .then(invocationOnMock -> { return expectedPedido;});

            Pedido returnedPedido = pedidoService.findById(VALID_ID);

            assertEquals(expectedPedido, returnedPedido);
        }

        @Test
        void shouldFindListOfPedido_whenDateTimeIsValid(){

            List<Pedido> expectedPedidos = PedidoBuilder
                    .getBuilder()
                    .buildList();

            Mockito.when(pedidoRepository.findAllByDataHora(Mockito.any()))
                    .then(invocationOnMock -> {return expectedPedidos;});

            List<Pedido> returnedPedidos = pedidoService.findAllByDataHora(VALID_ID);
            assertEquals(expectedPedidos, returnedPedidos);
        }

    }

    @Nested
    @DisplayName("Update pedido methods scenarios test")
    class UpdatePedidoTest{

        @Test
        void shouldThrowException_whenUpdatingAPedidoRemovingAllItensFromTheList(){

            PedidoDTO pedidoToUpdateDTO = PedidoBuilder
                    .getBuilder()
                    .validPedido()
                    .build();

            pedidoToUpdateDTO.getItemPedidoList().clear();

            Executable executable = () -> pedidoService.updatePedido(pedidoToUpdate);

            assertThrows(PedidoNotFoundException.class, executable);
        }

    }

}
