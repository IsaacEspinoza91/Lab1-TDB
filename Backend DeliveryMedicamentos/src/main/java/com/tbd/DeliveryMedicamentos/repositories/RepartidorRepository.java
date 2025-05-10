package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.entities.RepartidorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepartidorRepository {
    private final Sql2o sql2o;

    @Autowired
    public RepartidorRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<RepartidorEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Repartidores").executeAndFetch(RepartidorEntity.class);
        }
    }

    public RepartidorEntity findByUsuarioId(Integer usuarioId) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Repartidores WHERE usuario_id = :usuarioId")
                    .addParameter("usuarioId", usuarioId)
                    .executeAndFetchFirst(RepartidorEntity.class);
        }
    }

    public RepartidorEntity save(RepartidorEntity repartidor) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("INSERT INTO Repartidores(usuario_id, tipo_vehiculo) VALUES (:usuarioId, :tipoVehiculo)")
                    .addParameter("usuarioId", repartidor.getUsuario_id())
                    .addParameter("tipoVehiculo", repartidor.getTipo_vehiculo())
                    .executeUpdate();
            return repartidor;
        }
    }

    public void update(RepartidorEntity repartidor) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Repartidores SET tipo_vehiculo = :tipoVehiculo WHERE usuario_id = :usuarioId")
                    .addParameter("tipoVehiculo", repartidor.getTipo_vehiculo())
                    .addParameter("usuarioId", repartidor.getUsuario_id())
                    .executeUpdate();
        }
    }

    public void delete(Integer usuarioId) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Repartidores WHERE usuario_id = :usuarioId")
                    .addParameter("usuarioId", usuarioId)
                    .executeUpdate();
        }
    }
}