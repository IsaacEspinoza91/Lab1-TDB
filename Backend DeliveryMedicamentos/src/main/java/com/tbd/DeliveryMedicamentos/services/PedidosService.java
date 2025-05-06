package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.PedidosEntity;
import com.tbd.DeliveryMedicamentos.repositories.PedidosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidosService {
    private final PedidosRepository repository;

    public PedidosService(PedidosRepository repository) {
        this.repository = repository;
    }

    public List<PedidosEntity> findAll() {
        return repository.findAll();
    }

    public PedidosEntity findById(Integer id) {
        return repository.findById(id);
    }

    public PedidosEntity save(PedidosEntity pedido) {
        return repository.save(pedido);
    }

    public PedidosEntity update(PedidosEntity pedido) {
        repository.update(pedido);
        return repository.findById(pedido.getId());
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}