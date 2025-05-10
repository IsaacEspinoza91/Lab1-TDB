package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.ClienteEntity;
import com.tbd.DeliveryMedicamentos.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> getAllClientes() {
        List<ClienteEntity> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }


    @GetMapping("/{usuarioId}")
    public ResponseEntity<ClienteEntity> getClienteByUsuarioId(@PathVariable Integer usuarioId) {
        ClienteEntity cliente = clienteService.getClienteByUsuarioId(usuarioId);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> createCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity nuevoCliente = clienteService.createCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable Integer usuarioId,
                                       @RequestBody ClienteEntity cliente) {
        cliente.setUsuario_id(usuarioId);
        ClienteEntity clienteActualizado = clienteService.updateCliente(cliente);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer usuarioId) {
        clienteService.deleteCliente(usuarioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}