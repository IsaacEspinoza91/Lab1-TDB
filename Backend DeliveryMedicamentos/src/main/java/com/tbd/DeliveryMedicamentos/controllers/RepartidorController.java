package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.RepartidorEntity;
import com.tbd.DeliveryMedicamentos.services.RepartidorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorController {
    private final RepartidorService repartidorService;

    public RepartidorController(RepartidorService repartidorService) {
        this.repartidorService = repartidorService;
    }

    @GetMapping
    public ResponseEntity<List<RepartidorEntity>> findAll() {
        return ResponseEntity.ok(repartidorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepartidorEntity> findById(@PathVariable Integer id) {
        RepartidorEntity repartidor = repartidorService.findById(id);
        return repartidor != null ?
                ResponseEntity.ok(repartidor) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RepartidorEntity> create(@RequestBody RepartidorEntity repartidor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repartidorService.save(repartidor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepartidorEntity> update(@PathVariable Integer id, @RequestBody RepartidorEntity repartidor) {
        repartidor.setId(id);
        return ResponseEntity.ok(repartidorService.update(repartidor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        repartidorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}