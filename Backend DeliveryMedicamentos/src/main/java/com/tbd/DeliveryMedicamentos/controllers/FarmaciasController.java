package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.FarmaciasEntity;
import com.tbd.DeliveryMedicamentos.services.FarmaciasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmacias")
public class FarmaciasController {

    private final FarmaciasService farmaciasService;

    public FarmaciasController(FarmaciasService farmaciasService) {
        this.farmaciasService = farmaciasService;
    }

    // Get todas las farmacias
    @GetMapping
    public ResponseEntity<List<FarmaciasEntity>> findAll() {
        return ResponseEntity.ok(farmaciasService.findAll());
    }

    // Get de farmacia segun id
    @GetMapping("/{id}")
    public ResponseEntity<FarmaciasEntity> findById(@PathVariable Integer id) {
        FarmaciasEntity farmacia = farmaciasService.findById(id);
        return farmacia != null ? ResponseEntity.ok(farmacia) : ResponseEntity.notFound().build();
    }

    // Create de farmacia
    @PostMapping
    public ResponseEntity<FarmaciasEntity> create(@RequestBody FarmaciasEntity farmacia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(farmaciasService.save(farmacia));
    }

    // Update de farmacia
    @PutMapping("/{id}")
    public ResponseEntity<FarmaciasEntity> update(@PathVariable Integer id, @RequestBody FarmaciasEntity farmacia) {
        farmacia.setId(id);
        return ResponseEntity.ok(farmaciasService.update(farmacia));
    }

    // Delete de farmacia
    @DeleteMapping("/{id}")
    public ResponseEntity<FarmaciasEntity> delete(@PathVariable Integer id) {
        farmaciasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}