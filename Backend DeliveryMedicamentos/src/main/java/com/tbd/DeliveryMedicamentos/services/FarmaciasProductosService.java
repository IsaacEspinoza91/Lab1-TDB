package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.FarmaciasProductosEntity;
import com.tbd.DeliveryMedicamentos.repositories.FarmaciasProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciasProductosService {
    private final FarmaciasProductosRepository repository;

    public FarmaciasProductosService(FarmaciasProductosRepository repository) {
        this.repository = repository;
    }

    public List<FarmaciasProductosEntity> findAll() {
        return repository.findAll();
    }

    public FarmaciasProductosEntity findByFarmaciaProductoId(Integer farmaciaId, Integer productoId) {
        return repository.findByFarmaciaProductoId(farmaciaId, productoId);
    }

    public FarmaciasProductosEntity save(FarmaciasProductosEntity farmaciaProducto) {
        return repository.save(farmaciaProducto);
    }

    /*public FarmaciasProductosEntity update(FarmaciasProductosEntity farmaciaProducto) {
        repository.update(farmaciaProducto);
        return repository.findByFarmaciaProductoId(farmaciaProducto.getFarmacia_id(), farmaciaProducto.getProducto_id());
    }*/

    public void delete(Integer farmaciaId, Integer productoId) {
        repository.delete(farmaciaId, productoId);
    }
}