package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.entities.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    private final Sql2o sql2o;

    @Autowired
    public UsuarioRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<UsuarioEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Usuarios").executeAndFetch(UsuarioEntity.class);
        }
    }

    public UsuarioEntity findById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Usuarios WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(UsuarioEntity.class);
        }
    }

    public UsuarioEntity findByEmail(String email) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Usuarios WHERE email = :email")
                    .addParameter("email", email)
                    .executeAndFetchFirst(UsuarioEntity.class);
        }
    }

    public UsuarioEntity save(UsuarioEntity usuario) {
        try (Connection conn = sql2o.open()) {
            int id = (Integer) conn.createQuery("INSERT INTO Usuarios(rut, nombre, apellido, email, password, telefono, tipo) " +
                            "VALUES (:rut, :nombre, :apellido, :email, :password, :telefono, :tipo)", true)
                    .addParameter("rut", usuario.getRut())
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("apellido", usuario.getApellido())
                    .addParameter("email", usuario.getEmail())
                    .addParameter("password", usuario.getPassword())
                    .addParameter("telefono", usuario.getTelefono())
                    .addParameter("tipo", usuario.getTipo())
                    .executeUpdate()
                    .getKey();
            usuario.setId(id);
            return usuario;
        }
    }

    public void update(UsuarioEntity usuario) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Usuarios SET rut = :rut, nombre = :nombre, apellido = :apellido, " +
                            "email = :email, password = :password, telefono = :telefono, tipo = :tipo " +
                            "WHERE id = :id")
                    .addParameter("rut", usuario.getRut())
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("apellido", usuario.getApellido())
                    .addParameter("email", usuario.getEmail())
                    .addParameter("password", usuario.getPassword())
                    .addParameter("telefono", usuario.getTelefono())
                    .addParameter("tipo", usuario.getTipo())
                    .addParameter("id", usuario.getId())
                    .executeUpdate();
        }
    }

    public void delete(Integer id) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Usuarios WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public long count() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT COUNT(*) FROM Usuarios")
                    .executeScalar(Long.class);
        }
    }
}