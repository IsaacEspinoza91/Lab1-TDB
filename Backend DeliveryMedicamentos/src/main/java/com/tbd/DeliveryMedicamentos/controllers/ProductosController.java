package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.ProductosEntity;
import com.tbd.DeliveryMedicamentos.services.ProductosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public ResponseEntity<List<ProductosEntity>> findAll() {
        return ResponseEntity.ok(productosService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosEntity> findById(@PathVariable Integer id) {
        ProductosEntity producto = productosService.findById(id);
        return producto != null ?
                ResponseEntity.ok(producto) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductosEntity> create(@RequestBody ProductosEntity producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productosService.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductosEntity> update(@PathVariable Integer id, @RequestBody ProductosEntity producto) {
        producto.setId(id);
        return ResponseEntity.ok(productosService.update(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}