package br.com.desafio.totalshake.service;

import br.com.desafio.totalshake.builder.PedidoBuilder;
import br.com.desafio.totalshake.controller.dto.PedidoDTO;
import br.com.desafio.totalshake.domain.entity.Pedido;
import br.com.desafio.totalshake.domain.repository.PedidoRepository;
import br.com.desafio.totalshake.builder.PedidoDTOBuilder;
import br.com.desafio.totalshake.service.exceptions.*;
import br.com.desafio.totalshake.service.impl.PedidoServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    private static final long NEGATIVE_ID = -2;
    private static final long VALID_ID = 1;
    private static final long ZERO_ID = 0;
    private static final long NON_EXISTING_ID = Integer.MAX_VALUE;

    @BeforeAll
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("Save pedido method scenarios test")
    class SavePedidoTestDTO {

        @Test
        void shouldThrowException_whenSavingEmptyPedido(){

            PedidoDTO pedidoDTO = PedidoDTOBuilder
                    .getBuilder()
                    .emptyPedido()
                    .build();

            Executable executable = () -> pedidoService.save(pedidoDTO);

            assertThrows(EmptyPedidoException.class, executable);
        }

        @Test
        void shouldThrowException_whenSavingPedidoWithEmptyListOfItemPedido(){

            PedidoDTO pedidoDTO = PedidoDTOBuilder
                    .getBuilder()
                    .pedidoWithEmptyItemPedidoList()
                    .build();

            Executable executable = () -> pedidoService.save(pedidoDTO);

            assertThrows(EmptyItemPedidoListException.class, executable);
        }

        @Test
        void shouldThrowException_whenSavingPedidoWithDateTimeInFuture(){

            PedidoDTO pedidoDTO = PedidoDTOBuilder
                    .getBuilder()
                    .pedidoWithFutureDatetime()
                    .build();

            Executable executable = () -> pedidoService.save(pedidoDTO);

            assertThrows(FutureDateTimeException.class, executable);
        }

    }

    @Nested
    @DisplayName("Find pedido methods scenarios test")
    class FindPedidoTestDTO {

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

            Mockito.doAnswer(invocationOnMock -> {throw new PedidoNotFoundException();})
                    .when(pedidoRepository)
                    .findById(Mockito.anyLong());

            assertThrows(PedidoNotFoundException.class, executable);
        }

        @SneakyThrows
        @Test
        void shouldFindPedido_whenIdIsValid(){

            PedidoDTO expectedPedidoDTO = PedidoDTOBuilder
                    .getBuilder()
                    .validPedidoToReturn()
                    .build();

            Pedido expectedPedido = PedidoBuilder
                    .getBuilder()
                    .validPedidoToReturn()
                    .build();

            Mockito.doReturn(Optional.of(expectedPedido))
                    .when(pedidoRepository)
                    .findById(Mockito.anyLong());

            PedidoDTO returnedPedido = pedidoService.findById(VALID_ID);

            assertEquals(expectedPedidoDTO, returnedPedido);
        }
    }

    @Nested
    @DisplayName("Update pedido methods scenarios test")
    class UpdatePedidoTestDTO {

        @Test
        void shouldThrowException_whenUpdatingAPedidoRemovingAllItensFromTheList(){

            PedidoDTO pedidoDTOToUpdate = PedidoDTOBuilder
                    .getBuilder()
                    .pedidoWithEmptyItemPedidoList()
                    .build();

            pedidoDTOToUpdate.getItensPedidoList();

            Executable executable = () -> pedidoService.update(pedidoDTOToUpdate, VALID_ID);

            assertThrows(EmptyItemPedidoListException.class, executable);
        }

    }

}
