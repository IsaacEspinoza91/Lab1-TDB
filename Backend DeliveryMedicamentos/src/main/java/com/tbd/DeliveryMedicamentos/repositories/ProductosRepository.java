package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.entities.ProductosEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductosRepository {
    private final Sql2o sql2o;

    public ProductosRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<ProductosEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Productos").executeAndFetch(ProductosEntity.class);
        }
    }

    public ProductosEntity findById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Productos WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(ProductosEntity.class);
        }
    }

    public ProductosEntity save(ProductosEntity producto) {
        try (Connection conn = sql2o.open()) {
            int id = (Integer) conn.createQuery("INSERT INTO Productos(nombre, precio, stock, requiere_receta) " +
                            "VALUES (:nombre, :precio, :stock, :requiereReceta)", true)
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("stock", producto.getStock())
                    .addParameter("requiereReceta", producto.getRequiere_receta())
                    .executeUpdate()
                    .getKey();
            producto.setId(id);
            return producto;
        }
    }

    public void update(ProductosEntity producto) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Productos SET nombre = :nombre, precio = :precio, " +
                            "stock = :stock, requiere_receta = :requiereReceta WHERE id = :id")
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("stock", producto.getStock())
                    .addParameter("requiereReceta", producto.getRequiere_receta())
                    .addParameter("id", producto.getId())
                    .executeUpdate();
        }
    }

    public void delete(Integer id) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Productos WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}