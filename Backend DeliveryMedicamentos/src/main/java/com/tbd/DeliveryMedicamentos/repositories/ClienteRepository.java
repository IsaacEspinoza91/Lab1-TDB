package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.entities.ClienteEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository {
    private final Sql2o sql2o;

    public ClienteRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<ClienteEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Clientes").executeAndFetch(ClienteEntity.class);
        }
    }

    public ClienteEntity findById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Clientes WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(ClienteEntity.class);
        }
    }

    public ClienteEntity save(ClienteEntity cliente) {
        try (Connection conn = sql2o.open()) {
            int id = (Integer) conn.createQuery("INSERT INTO Clientes(rut, nombre, apellido, direccion, email, telefono) " +
                            "VALUES (:rut, :nombre, :apellido, :direccion, :email, :telefono)", true)
                    .addParameter("rut", cliente.getRut())
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("apellido", cliente.getApellido())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .executeUpdate()
                    .getKey();
            cliente.setId(id);
            return cliente;
        }
    }

    public void update(ClienteEntity cliente) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Clientes SET rut = :rut, nombre = :nombre, apellido = :apellido, " +
                            "direccion = :direccion, email = :email, telefono = :telefono WHERE id = :id")
                    .addParameter("rut", cliente.getRut())
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("apellido", cliente.getApellido())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .addParameter("id", cliente.getId())
                    .executeUpdate();
        }
    }

    public void delete(Integer id) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Clientes WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}