package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.entities.FarmaciasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FarmaciasRepository {

    private final Sql2o sql2o;

    @Autowired
    public FarmaciasRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<FarmaciasEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id, nombre, lugar FROM Farmacias")
                    .executeAndFetch(FarmaciasEntity.class);
        }
    }

    public FarmaciasEntity findById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT id, nombre, lugar FROM Farmacias WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(FarmaciasEntity.class);
        }
    }

    public FarmaciasEntity save(FarmaciasEntity farmacia) {
        try (Connection conn = sql2o.open()) {
            int id = (Integer) conn.createQuery("INSERT INTO Farmacias(nombre, lugar) VALUES (:nombre, :lugar)", true)
                    .addParameter("nombre", farmacia.getNombre())
                    .addParameter("lugar", farmacia.getLugar())
                    .executeUpdate()
                    .getKey();

            farmacia.setId(id);
            return farmacia;
        }
    }

    public void update(FarmaciasEntity farmacia) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Farmacias SET nombre = :nombre, lugar = :lugar WHERE id = :id")
                    .addParameter("nombre", farmacia.getNombre())
                    .addParameter("lugar", farmacia.getLugar())
                    .addParameter("id", farmacia.getId())
                    .executeUpdate();
        }
    }

    public void delete(Integer id) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Farmacias WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public long count() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT COUNT(*) FROM Farmacias")
                    .executeScalar(Long.class);
        }
    }

    public List<Map<String, Object>> obtenerFarmaciasMayorVolumenEntregado() {
        String sql = "SELECT * FROM farmacias_volumen_entregas_exitosas";
        return sql2o.open().createQuery(sql).executeAndFetchTable().asList();
    }
}