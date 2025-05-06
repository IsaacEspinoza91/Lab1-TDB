package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.MedioDePagoEntity;
import com.tbd.DeliveryMedicamentos.services.MedioDePagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medios_de_pago")
public class MedioDePagoController {
    private final MedioDePagoService medioDePagoService;

    public MedioDePagoController(MedioDePagoService medioDePagoService) {
        this.medioDePagoService = medioDePagoService;
    }

    @GetMapping
    public ResponseEntity<List<MedioDePagoEntity>> findAll() {
        return ResponseEntity.ok(medioDePagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedioDePagoEntity> findById(@PathVariable Integer id) {
        MedioDePagoEntity medio = medioDePagoService.findById(id);
        return medio != null ?
                ResponseEntity.ok(medio) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MedioDePagoEntity> create(@RequestBody MedioDePagoEntity medio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medioDePagoService.save(medio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedioDePagoEntity> update(@PathVariable Integer id, @RequestBody MedioDePagoEntity medio) {
        medio.setId(id);
        return ResponseEntity.ok(medioDePagoService.update(medio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medioDePagoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}