package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.CalificacionesEntity;
import com.tbd.DeliveryMedicamentos.repositories.CalificacionesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionesService {
    private final CalificacionesRepository repository;

    public CalificacionesService(CalificacionesRepository repository) {
        this.repository = repository;
    }

    public List<CalificacionesEntity> findAll() {
        return repository.findAll();
    }

    public CalificacionesEntity findById(Integer id) {
        return repository.findById(id);
    }

    public CalificacionesEntity save(CalificacionesEntity calificacion) {
        return repository.save(calificacion);
    }

    public CalificacionesEntity update(CalificacionesEntity calificacion) {
        repository.update(calificacion);
        return repository.findById(calificacion.getId());
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}