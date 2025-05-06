package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.FarmaciasEntity;
import com.tbd.DeliveryMedicamentos.repositories.FarmaciasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmaciasService {

    private final FarmaciasRepository farmaciasRepository;

    public FarmaciasService(FarmaciasRepository farmaciasRepository) {
        this.farmaciasRepository = farmaciasRepository;
    }

    // Metodos
    public List<FarmaciasEntity> findAll() {
        return farmaciasRepository.findAll();
    }

    public FarmaciasEntity findById(Integer id) {
        return farmaciasRepository.findById(id);
    }

    public FarmaciasEntity save(FarmaciasEntity farmaciasEntity) {
        return farmaciasRepository.save(farmaciasEntity);
    }

    public FarmaciasEntity update(FarmaciasEntity farmaciasEntity) {
        farmaciasRepository.update(farmaciasEntity);
        return farmaciasRepository.findById(farmaciasEntity.getId());
    }

    public void delete(Integer id) {
        farmaciasRepository.delete(id);
    }
}