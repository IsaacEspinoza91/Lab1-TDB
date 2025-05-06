package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.PedidosEntity;
import com.tbd.DeliveryMedicamentos.services.PedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {
    private final PedidosService pedidosService;

    public PedidosController(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @GetMapping
    public ResponseEntity<List<PedidosEntity>> findAll() {
        return ResponseEntity.ok(pedidosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidosEntity> findById(@PathVariable Integer id) {
        PedidosEntity pedido = pedidosService.findById(id);
        return pedido != null ?
                ResponseEntity.ok(pedido) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PedidosEntity> create(@RequestBody PedidosEntity pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidosService.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidosEntity> update(@PathVariable Integer id, @RequestBody PedidosEntity pedido) {
        pedido.setId(id);
        return ResponseEntity.ok(pedidosService.update(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}