package com.tbd.DeliveryMedicamentos.entities;

public class FarmaciasProductosEntity {
    private int farmacia_id;   // FK a Farmacia
    private int producto_id;   // FK a Producto
    private int stock;

    // Constructores
    public FarmaciasProductosEntity() {}

    public FarmaciasProductosEntity(int farmaciaId, int productoId, int stock) {
        this.farmacia_id = farmaciaId;
        this.producto_id = productoId;
        this.stock = stock;
    }

    // Getters
    public int getFarmacia_id() {
        return farmacia_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setFarmacia_id(int farmacia_id) {
        this.farmacia_id = farmacia_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
