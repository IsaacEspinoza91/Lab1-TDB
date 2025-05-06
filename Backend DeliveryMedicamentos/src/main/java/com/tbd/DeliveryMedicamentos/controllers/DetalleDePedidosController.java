package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.DetalleDePedidosEntity;
import com.tbd.DeliveryMedicamentos.services.DetalleDePedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle_pedidos")
public class DetalleDePedidosController {
    private final DetalleDePedidosService detalleDePedidosService;

    public DetalleDePedidosController(DetalleDePedidosService detalleDePedidosService) {
        this.detalleDePedidosService = detalleDePedidosService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleDePedidosEntity>> findAll() {
        return ResponseEntity.ok(detalleDePedidosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleDePedidosEntity> findById(@PathVariable Integer id) {
        DetalleDePedidosEntity detalle = detalleDePedidosService.findById(id);
        return detalle != null ?
                ResponseEntity.ok(detalle) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DetalleDePedidosEntity> create(@RequestBody DetalleDePedidosEntity detalle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleDePedidosService.save(detalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleDePedidosEntity> update(@PathVariable Integer id, @RequestBody DetalleDePedidosEntity detalle) {
        detalle.setId(id);
        return ResponseEntity.ok(detalleDePedidosService.update(detalle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        detalleDePedidosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}