package br.com.desafio.totalshake.controller;

import br.com.desafio.totalshake.controller.dto.PedidoDTO;
import br.com.desafio.totalshake.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<EntityModel<PedidoDTO>> getPedidoById(@PathVariable Long id) {

        EntityModel<PedidoDTO> pedidoDTOEntityModel = EntityModel.of(pedidoService.findById(id));

        WebMvcLinkBuilder linkToPedidos = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPedidos());

        pedidoDTOEntityModel.add(linkToPedidos.withRel("pedidos"));

        return ResponseEntity.ok(pedidoDTOEntityModel);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getPedidos(){
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
