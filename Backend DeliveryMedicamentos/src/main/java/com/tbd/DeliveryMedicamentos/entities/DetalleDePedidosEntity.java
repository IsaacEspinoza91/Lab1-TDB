package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class DetalleDePedidosEntity {
    private int id;
    private int pedidoId;
    private int productoId;
    private int cantidad;

    // Constructores
    public DetalleDePedidosEntity() {}

    public DetalleDePedidosEntity(int id, int pedidoId, int productoId, int cantidad) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
