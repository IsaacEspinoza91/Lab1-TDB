package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.ProductosEntity;
import com.tbd.DeliveryMedicamentos.repositories.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService {
    private final ProductosRepository repository;

    public ProductosService(ProductosRepository repository) {
        this.repository = repository;
    }

    public List<ProductosEntity> findAll() {
        return repository.findAll();
    }

    public ProductosEntity findById(Integer id) {
        return repository.findById(id);
    }

    public ProductosEntity save(ProductosEntity producto) {
        return repository.save(producto);
    }

    public ProductosEntity update(ProductosEntity producto) {
        repository.update(producto);
        return repository.findById(producto.getId());
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}