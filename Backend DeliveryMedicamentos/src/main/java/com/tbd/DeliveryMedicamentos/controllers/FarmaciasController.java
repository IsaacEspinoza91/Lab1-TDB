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
    public ResponseEntity<List<FarmaciasEntity>> getAllFarmacias() {
        return ResponseEntity.ok(farmaciasService.getAllFarmacias());
    }

    // Get de farmacia segun id
    @GetMapping("/{id}")
    public ResponseEntity<FarmaciasEntity> getFarmaciaById(@PathVariable int id) {
        FarmaciasEntity farmacia = farmaciasService.getFarmaciaById(id);
        return farmacia != null ? ResponseEntity.ok(farmacia) : ResponseEntity.notFound().build();
    }

    // Create de farmacia
    @PostMapping
    public ResponseEntity<FarmaciasEntity> createFarmacia(@RequestBody FarmaciasEntity farmacia) {
        FarmaciasEntity newFarmacia = farmaciasService.createFarmacia(farmacia);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFarmacia);
    }

    // Update de farmacia
    @PutMapping("/{id}")
    public ResponseEntity<FarmaciasEntity> updateFarmacia(@PathVariable int id, @RequestBody FarmaciasEntity farmacia) {
        farmacia.setId(id);
        FarmaciasEntity farmaciaUpdate = farmaciasService.updateFarmacia(farmacia);
        return ResponseEntity.ok(farmaciaUpdate);
    }

    // Delete de farmacia
    @DeleteMapping("/{id}")
    public ResponseEntity<FarmaciasEntity> deleteFarmacia(@PathVariable int id) {
        farmaciasService.deleteFarmaciaById(id);
        return ResponseEntity.noContent().build();
    }
}