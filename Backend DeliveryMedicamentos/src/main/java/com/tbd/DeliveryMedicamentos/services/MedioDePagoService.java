package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.MedioDePagoEntity;
import com.tbd.DeliveryMedicamentos.repositories.MedioDePagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedioDePagoService {
    private final MedioDePagoRepository repository;

    public MedioDePagoService(MedioDePagoRepository repository) {
        this.repository = repository;
    }

    public List<MedioDePagoEntity> findAll() {
        return repository.findAll();
    }

    public MedioDePagoEntity findById(Integer id) {
        return repository.findById(id);
    }

    public MedioDePagoEntity save(MedioDePagoEntity medioDePago) {
        return repository.save(medioDePago);
    }

    public MedioDePagoEntity update(MedioDePagoEntity medioDePago) {
        repository.update(medioDePago);
        return repository.findById(medioDePago.getId());
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}