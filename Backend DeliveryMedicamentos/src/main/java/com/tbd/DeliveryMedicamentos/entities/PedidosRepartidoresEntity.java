package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class PedidosRepartidoresEntity {

    private int pedidoId;      // FK a Pedido
    private int repartidorId;  // FK a Repartidor

    // Constructores
    public PedidosRepartidoresEntity() {}

    public PedidosRepartidoresEntity(int pedidoId, int repartidorId) {
        this.pedidoId = pedidoId;
        this.repartidorId = repartidorId;
    }

    // Getters
    public int getPedidoId() {
        return pedidoId;
    }

    public int getRepartidorId() {
        return repartidorId;
    }

    // Setters
    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setRepartidorId(int repartidorId) {
        this.repartidorId = repartidorId;
    }

}
