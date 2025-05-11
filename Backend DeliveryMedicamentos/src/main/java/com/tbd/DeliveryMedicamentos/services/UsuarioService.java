package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.entities.UsuarioEntity;
import com.tbd.DeliveryMedicamentos.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }

    public List<UsuarioEntity> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity getUsuarioById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioEntity getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioEntity createUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity updateUsuario(UsuarioEntity usuario) {
        usuarioRepository.update(usuario);
        return usuario;
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.delete(id);
    }

    public long countUsuarios() {
        return usuarioRepository.count();
    }
}