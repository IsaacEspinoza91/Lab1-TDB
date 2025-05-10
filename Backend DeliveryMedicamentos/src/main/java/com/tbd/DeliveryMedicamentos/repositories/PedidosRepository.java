package com.tbd.DeliveryMedicamentos.repositories;

import com.tbd.DeliveryMedicamentos.DTO.DetallePedidoDTO;
import com.tbd.DeliveryMedicamentos.DTO.ResumenPedidoClienteDTO;
import com.tbd.DeliveryMedicamentos.entities.PedidosEntity;
import com.tbd.DeliveryMedicamentos.utils.ConverJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<ResumenPedidoClienteDTO> obtenerResumenPedidos() {
        String sql = "SELECT * FROM resumen_pedidos_por_cliente";

        try (Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .executeAndFetch(ResumenPedidoClienteDTO.class);
        }
    }

    public void cambiarEstadoPedido(int idPedido, String nuevoEstado) {
        String sql = "CALL cambiar_estado_pedido(:idPedido, :nuevoEstado)";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("idPedido", idPedido)
                    .addParameter("nuevoEstado", nuevoEstado)
                    .executeUpdate();
        } catch (Exception e) {
            // El mensaje de error vendrá de las RAISE EXCEPTION
            throw new RuntimeException(e.getMessage());
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

    public List<Map<String, Object>> tiempoPromedioPorRepartidor() {
        try (Connection conn = sql2o.open()) {
            String sql = """
        SELECT 
            u.nombre AS repartidores_nombre,
            ROUND(AVG(p.fecha_entrega - p.fecha), 1) AS tiempo_promedio_dias
        FROM Pedidos p
        JOIN Repartidores r ON p.repartidor_id = r.usuario_id
        JOIN Usuarios u ON r.usuario_id = u.id
        WHERE p.fecha_entrega IS NOT NULL
        GROUP BY u.id, u.nombre;
        """;
            return conn.createQuery(sql)
                    .executeAndFetchTable()
                    .asList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Map<String, Object>> tiempoPromedioPorRepartidorId(int repartidorId) {
        try (Connection conn = sql2o.open()) {
            String sql = """
        SELECT 
            u.nombre AS repartidores_nombre,
            ROUND(AVG(p.fecha_entrega - p.fecha), 1) AS tiempo_promedio_dias
        FROM Pedidos p
        JOIN Repartidores r ON p.repartidor_id = r.usuario_id
        JOIN Usuarios u ON r.usuario_id = u.id
        WHERE p.fecha_entrega IS NOT NULL
        AND r.usuario_id = :repartidorId
        GROUP BY u.id, u.nombre;
        """;
            return conn.createQuery(sql)
                    .addParameter("repartidorId", repartidorId)
                    .executeAndFetchTable()
                    .asList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }



}