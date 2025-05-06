package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.ClienteEntity;
import com.tbd.DeliveryMedicamentos.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> findById(@PathVariable Integer id) {
        ClienteEntity cliente = clienteService.findById(id);
        return cliente != null ?
                ResponseEntity.ok(cliente) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> create(@RequestBody ClienteEntity cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteEntity> update(@PathVariable Integer id, @RequestBody ClienteEntity cliente) {
        cliente.setId(id);
        return ResponseEntity.ok(clienteService.update(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}