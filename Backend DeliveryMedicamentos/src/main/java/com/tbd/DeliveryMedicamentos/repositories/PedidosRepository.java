package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.DTO.DetallePedidoDTO;
import com.tbd.DeliveryMedicamentos.entities.PedidosEntity;
import com.tbd.DeliveryMedicamentos.utils.ConverJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidosRepository {
    private final Sql2o sql2o;

    @Autowired
    public PedidosRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<PedidosEntity> findAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Pedidos").executeAndFetch(PedidosEntity.class);
        }
    }

    public PedidosEntity findById(Integer id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM Pedidos WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(PedidosEntity.class);
        }
    }

    public PedidosEntity save(PedidosEntity pedido) {
        try (Connection conn = sql2o.open()) {
            int id = (Integer) conn.createQuery("INSERT INTO Pedidos(fecha, urgencia, total_pagado, estado_entrega, " +
                            "fecha_entrega, cliente_id, medio_pago_id, farmacia_id, repartidor_id) " +
                            "VALUES (:fecha, :urgencia, :totalPagado, :estadoEntrega, :fechaEntrega, " +
                            ":clienteId, :medioPagoId, :farmaciaId, :repartidorId)", true)
                    .addParameter("fecha", pedido.getFecha())
                    .addParameter("urgencia", pedido.getUrgencia())
                    .addParameter("totalPagado", pedido.getTotal_pagado())
                    .addParameter("estadoEntrega", pedido.getEstado_entrega())
                    .addParameter("fechaEntrega", pedido.getFecha_entrega())
                    .addParameter("clienteId", pedido.getCliente_id())
                    .addParameter("medioPagoId", pedido.getMedio_pago_id())
                    .addParameter("farmaciaId", pedido.getFarmacia_id())
                    .addParameter("repartidorId", pedido.getRepartidor_id())
                    .executeUpdate()
                    .getKey();
            pedido.setId(id);
            return pedido;
        }
    }

    public void registrarPedido(PedidosEntity pedido, List<DetallePedidoDTO> detalles) {
        String sql = "CALL registrar_pedido(" +
                "CAST(:fecha AS date), " +
                "CAST(:urgencia AS boolean), " +
                "CAST(:total_pagado AS integer), " +
                "CAST(:estado_entrega AS varchar), " +
                "CAST(:fecha_entrega AS date), " +
                "CAST(:medio_pago_id AS integer), " +
                "CAST(:farmacia_id AS integer), " +
                "CAST(:repartidor_id AS integer), " +
                "CAST(:cliente_id AS integer), " +
                "CAST(:detalles AS json))";
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("fecha", pedido.getFecha())
                    .addParameter("urgencia", pedido.getUrgencia())
                    .addParameter("total_pagado", pedido.getTotal_pagado())
                    .addParameter("estado_entrega", pedido.getEstado_entrega())
                    .addParameter("fecha_entrega", pedido.getFecha_entrega())
                    .addParameter("medio_pago_id", pedido.getMedio_pago_id())
                    .addParameter("farmacia_id", pedido.getFarmacia_id())
                    .addParameter("repartidor_id", pedido.getRepartidor_id())
                    .addParameter("cliente_id", pedido.getCliente_id())
                    .addParameter("detalles", ConverJsonUtil.toJson(detalles))
                    .executeUpdate();
        }
    }



    public void update(PedidosEntity pedido) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("UPDATE Pedidos SET fecha = :fecha, urgencia = :urgencia, total_pagado = :totalPagado, " +
                            "estado_entrega = :estadoEntrega, fecha_entrega = :fechaEntrega, cliente_id = :clienteId, " +
                            "medio_pago_id = :medioPagoId, farmacia_id = :farmaciaId, repartidor_id = :repartidorId " +
                            "WHERE id = :id")
                    .addParameter("fecha", pedido.getFecha())
                    .addParameter("urgencia", pedido.getUrgencia())
                    .addParameter("totalPagado", pedido.getTotal_pagado())
                    .addParameter("estadoEntrega", pedido.getEstado_entrega())
                    .addParameter("fechaEntrega", pedido.getFecha_entrega())
                    .addParameter("clienteId", pedido.getCliente_id())
                    .addParameter("medioPagoId", pedido.getMedio_pago_id())
                    .addParameter("farmaciaId", pedido.getFarmacia_id())
                    .addParameter("repartidorId", pedido.getRepartidor_id())
                    .addParameter("id", pedido.getId())
                    .executeUpdate();
        }
    }

    public void delete(Integer id) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM Pedidos WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}