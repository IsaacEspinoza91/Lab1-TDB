package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.RepartidorEntity;
import com.tbd.DeliveryMedicamentos.repositories.RepartidorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepartidorService {
    private final RepartidorRepository repository;

    public RepartidorService(RepartidorRepository repository) {
        this.repository = repository;
    }

    public List<RepartidorEntity> findAll() {
        return repository.findAll();
    }

    public RepartidorEntity findById(Integer id) {
        return repository.findById(id);
    }

    public RepartidorEntity save(RepartidorEntity repartidor) {
        return repository.save(repartidor);
    }

    public RepartidorEntity update(RepartidorEntity repartidor) {
        repository.update(repartidor);
        return repository.findById(repartidor.getId());
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}