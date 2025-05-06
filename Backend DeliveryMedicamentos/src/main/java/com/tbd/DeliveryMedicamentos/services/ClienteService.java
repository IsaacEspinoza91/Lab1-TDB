package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.ClienteEntity;
import com.tbd.DeliveryMedicamentos.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteEntity> findAll() {
        return repository.findAll();
    }

    public ClienteEntity findById(Integer id) {
        return repository.findById(id);
    }

    public ClienteEntity save(ClienteEntity cliente) {
        return repository.save(cliente);
    }

    public ClienteEntity update(ClienteEntity cliente) {
        repository.update(cliente);
        return repository.findById(cliente.getId());
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}