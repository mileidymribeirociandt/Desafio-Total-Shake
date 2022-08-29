package br.com.desafio.totalshake.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DefaultController {

    @GetMapping("/OK")
    public ResponseEntity<String> ok(){
        return ResponseEntity.ok("OK - " + LocalDateTime.now().toString());
    }

}
