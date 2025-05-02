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
    public List<FarmaciasEntity> getAllFarmacias() {
        return farmaciasRepository.findAll();
    }

    public FarmaciasEntity getFarmaciaById(int id) {
        return farmaciasRepository.findById(id);
    }

    public FarmaciasEntity createFarmacia(FarmaciasEntity farmaciasEntity) {
        return farmaciasRepository.save(farmaciasEntity);
    }

    public FarmaciasEntity updateFarmacia(FarmaciasEntity farmaciasEntity) {
        farmaciasRepository.update(farmaciasEntity);
        return farmaciasRepository.findById(farmaciasEntity.getId());
    }

    public void deleteFarmaciaById(int id) {
        farmaciasRepository.delete(id);
    }
}