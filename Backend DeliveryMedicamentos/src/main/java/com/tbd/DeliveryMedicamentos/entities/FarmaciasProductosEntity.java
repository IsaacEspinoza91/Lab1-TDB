package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class FarmaciasProductosEntity {
    private int farmaciaId;   // FK a Farmacia
    private int productoId;   // FK a Producto

    // Constructores
    public FarmaciasProductosEntity() {}

    public FarmaciasProductosEntity(int farmaciaId, int productoId) {
        this.farmaciaId = farmaciaId;
        this.productoId = productoId;
    }

    // Getters
    public int getFarmaciaId() {
        return farmaciaId;
    }

    public int getProductoId() {
        return productoId;
    }

    // Setters
    public void setFarmaciaId(int farmaciaId) {
        this.farmaciaId = farmaciaId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

}
