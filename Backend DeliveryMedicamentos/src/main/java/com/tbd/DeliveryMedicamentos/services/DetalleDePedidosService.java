package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.DetalleDePedidosEntity;
import com.tbd.DeliveryMedicamentos.repositories.DetalleDePedidosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleDePedidosService {
    private final DetalleDePedidosRepository repository;

    public DetalleDePedidosService(DetalleDePedidosRepository repository) {
        this.repository = repository;
    }

    public List<DetalleDePedidosEntity> findAll() {
        return repository.findAll();
    }

    public DetalleDePedidosEntity findById(Integer id) {
        return repository.findById(id);
    }

    public DetalleDePedidosEntity save(DetalleDePedidosEntity detalle) {
        return repository.save(detalle);
    }

    public DetalleDePedidosEntity update(DetalleDePedidosEntity detalle) {
        repository.update(detalle);
        return repository.findById(detalle.getId());
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}