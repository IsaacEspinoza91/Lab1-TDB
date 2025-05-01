package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class PedidosFarmaciasEntity {
    private int pedidoId;    // FK a Pedido
    private int farmaciaId;  // FK a Farmacia

    // Constructores
    public PedidosFarmaciasEntity() {}

    public PedidosFarmaciasEntity(int pedidoId, int farmaciaId) {
        this.pedidoId = pedidoId;
        this.farmaciaId = farmaciaId;
    }

    // Getters
    public int getPedidoId() {
        return pedidoId;
    }

    public int getFarmaciaId() {
        return farmaciaId;
    }

    // Setters
    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setFarmaciaId(int farmaciaId) {
        this.farmaciaId = farmaciaId;
    }
}
