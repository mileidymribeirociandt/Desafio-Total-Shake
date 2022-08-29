package br.com.desafio.totalshake.controller;

import br.com.desafio.totalshake.controller.dto.PedidoDTO;
import br.com.desafio.totalshake.service.PedidoService;
import br.com.desafio.totalshake.service.exceptions.PedidoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoController {
    //https://www.toptal.com/java/spring-boot-rest-api-error-handling#:~:text=ExceptionHandler%20is%20a%20Spring%20annotation,thrown%20within%20this%20controller%20only.
    //https://www.bezkoder.com/spring-boot-restcontrolleradvice/
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> savePedido(@RequestBody @Valid PedidoDTO pedidoDTO){
        return new ResponseEntity<>(pedidoService.save(pedidoDTO), HttpStatus.OK);
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PedidoDTO>> findAlldPedidosBy(){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable Long id, @RequestBody @Valid PedidoDTO pedidoDTO){
         return ResponseEntity.ok(pedidoService.update(pedidoDTO, id));

    }

    @DeleteMapping("/pedido/delete/{id}")
    public ResponseEntity<String> deletePedidoById(@PathVariable Long id){
        pedidoService.deleteById(id);
        return ResponseEntity.ok("Pedido deletado");
    }


}
