package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.entities.RepartidorEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepartidorRepository {
    private final Sql2o sql2o;

    public RepartidorRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<RepartidorEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Repartidores").executeAndFetch(RepartidorEntity.class);
        }
    }

    public RepartidorEntity findById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Repartidores WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(RepartidorEntity.class);
        }
    }

    public RepartidorEntity save(RepartidorEntity repartidor) {
        try (Connection conn = sql2o.open()) {
            int id = (Integer) conn.createQuery("INSERT INTO Repartidores(rut, nombre, telefono) VALUES (:rut, :nombre, :telefono)", true)
                    .addParameter("rut", repartidor.getRut())
                    .addParameter("nombre", repartidor.getNombre())
                    .addParameter("telefono", repartidor.getTelefono())
                    .executeUpdate()
                    .getKey();
            repartidor.setId(id);
            return repartidor;
        }
    }

    public void update(RepartidorEntity repartidor) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Repartidores SET rut = :rut, nombre = :nombre, telefono = :telefono WHERE id = :id")
                    .addParameter("rut", repartidor.getRut())
                    .addParameter("nombre", repartidor.getNombre())
                    .addParameter("telefono", repartidor.getTelefono())
                    .addParameter("id", repartidor.getId())
                    .executeUpdate();
        }
    }

    public void delete(Integer id) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Repartidores WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}