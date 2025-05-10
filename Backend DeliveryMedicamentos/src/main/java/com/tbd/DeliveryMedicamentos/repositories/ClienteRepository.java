package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.entities.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository {
    private final Sql2o sql2o;

    @Autowired
    public ClienteRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<ClienteEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Clientes").executeAndFetch(ClienteEntity.class);
        }
    }

    public ClienteEntity findByUsuarioId(Integer usuarioId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Clientes WHERE usuario_id = :usuarioId")
                    .addParameter("usuarioId", usuarioId)
                    .executeAndFetchFirst(ClienteEntity.class);
        }
    }

    public ClienteEntity save(ClienteEntity cliente) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("INSERT INTO Clientes(usuario_id, direccion) VALUES (:usuarioId, :direccion)")
                    .addParameter("usuarioId", cliente.getUsuario_id())
                    .addParameter("direccion", cliente.getDireccion())
                    .executeUpdate();
            return cliente;
        }
    }

    public void update(ClienteEntity cliente) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Clientes SET direccion = :direccion WHERE usuario_id = :usuarioId")
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("usuarioId", cliente.getUsuario_id())
                    .executeUpdate();
        }
    }

    public void delete(Integer usuarioId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Clientes WHERE usuario_id = :usuarioId")
                    .addParameter("usuarioId", usuarioId)
                    .executeUpdate();
        }
    }
}