package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.entities.DetalleDePedidosEntity;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetalleDePedidosRepository {
    private final Sql2o sql2o;

    public DetalleDePedidosRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<DetalleDePedidosEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Detalle_de_pedidos").executeAndFetch(DetalleDePedidosEntity.class);
        }
    }

    public DetalleDePedidosEntity findById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Detalle_de_pedidos WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DetalleDePedidosEntity.class);
        }
    }

    public DetalleDePedidosEntity save(DetalleDePedidosEntity detalle) {
        try (Connection conn = sql2o.open()) {
            int id = (Integer) conn.createQuery("INSERT INTO Detalle_de_pedidos(pedido_id, producto_id, cantidad) " +
                            "VALUES (:pedidoId, :productoId, :cantidad)", true)
                    .addParameter("pedidoId", detalle.getPedido_id())
                    .addParameter("productoId", detalle.getProducto_id())
                    .addParameter("cantidad", detalle.getCantidad())
                    .executeUpdate()
                    .getKey();
            detalle.setId(id);
            return detalle;
        }
    }

    public void update(DetalleDePedidosEntity detalle) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Detalle_de_pedidos SET pedido_id = :pedidoId, producto_id = :productoId, " +
                            "cantidad = :cantidad WHERE id = :id")
                    .addParameter("pedidoId", detalle.getPedido_id())
                    .addParameter("productoId", detalle.getProducto_id())
                    .addParameter("cantidad", detalle.getCantidad())
                    .addParameter("id", detalle.getId())
                    .executeUpdate();
        }
    }

    public void delete(Integer id) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Detalle_de_pedidos WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}