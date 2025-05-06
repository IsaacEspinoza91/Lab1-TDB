package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.FarmaciasProductosEntity;
import com.tbd.DeliveryMedicamentos.services.FarmaciasProductosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmacias_productos")
public class FarmaciasProductosController {
    private final FarmaciasProductosService farmaciasProductosService;

    public FarmaciasProductosController(FarmaciasProductosService farmaciasProductosService) {
        this.farmaciasProductosService = farmaciasProductosService;
    }

    @GetMapping
    public ResponseEntity<List<FarmaciasProductosEntity>> findAll() {
        return ResponseEntity.ok(farmaciasProductosService.findAll());
    }

    @GetMapping("/farmacia/{farmaciaId}/producto/{productoId}")
    public ResponseEntity<FarmaciasProductosEntity> findByFarmaciaAndProducto(
            @PathVariable Integer farmaciaId, @PathVariable Integer productoId) {
        FarmaciasProductosEntity fp = farmaciasProductosService.findByFarmaciaProductoId(farmaciaId, productoId);
        return fp != null ?
                ResponseEntity.ok(fp) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FarmaciasProductosEntity> create(@RequestBody FarmaciasProductosEntity fp) {
        return ResponseEntity.status(HttpStatus.CREATED).body(farmaciasProductosService.save(fp));
    }

    /*@PutMapping("/farmacia/{farmaciaId}/producto/{productoId}")
    public ResponseEntity<FarmaciasProductosEntity> update(
            @PathVariable Integer farmaciaId, @PathVariable Integer productoId,
            @RequestBody FarmaciasProductosEntity fp) {
        fp.setFarmacia_id(farmaciaId);
        fp.setProducto_id(productoId);
        return ResponseEntity.ok(farmaciasProductosService.update(fp));
    }*/

    @DeleteMapping("/farmacia/{farmaciaId}/producto/{productoId}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer farmaciaId, @PathVariable Integer productoId) {
        farmaciasProductosService.delete(farmaciaId, productoId);
        return ResponseEntity.noContent().build();
    }
}