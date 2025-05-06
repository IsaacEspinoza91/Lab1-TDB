package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.CalificacionesEntity;
import com.tbd.DeliveryMedicamentos.services.CalificacionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionesController {
    private final CalificacionesService calificacionesService;

    public CalificacionesController(CalificacionesService calificacionesService) {
        this.calificacionesService = calificacionesService;
    }

    @GetMapping
    public ResponseEntity<List<CalificacionesEntity>> findAll() {
        return ResponseEntity.ok(calificacionesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionesEntity> findById(@PathVariable Integer id) {
        CalificacionesEntity calificacion = calificacionesService.findById(id);
        return calificacion != null ?
                ResponseEntity.ok(calificacion) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CalificacionesEntity> create(@RequestBody CalificacionesEntity calificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionesService.save(calificacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionesEntity> update(@PathVariable Integer id, @RequestBody CalificacionesEntity calificacion) {
        calificacion.setId(id);
        return ResponseEntity.ok(calificacionesService.update(calificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        calificacionesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}