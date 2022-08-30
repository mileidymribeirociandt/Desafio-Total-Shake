package br.com.desafio.totalshake.controller;

import br.com.desafio.totalshake.controller.dto.PedidoDTO;
import br.com.desafio.totalshake.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/pedidos")
public class PedidoController {
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

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getAllPedidos(){
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @PutMapping("/pedido/{id}")
    public ResponseEntity<PedidoDTO> updatePedido(@PathVariable(required = true) Long id, @RequestBody @Valid PedidoDTO pedidoDTO){
        return ResponseEntity.ok(pedidoService.update(pedidoDTO, id));

    }

    @DeleteMapping("/pedido/{id}/delete")
    public ResponseEntity<String> deletePedidoById(@PathVariable Long id){
        pedidoService.deleteById(id);
        return ResponseEntity.ok("Pedido deletado");
    }


}
