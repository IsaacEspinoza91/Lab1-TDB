package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class ProductosEntity {
    private int id;
    private String nombre;
    private int precio;
    private int stock;
    private boolean requiereReceta;

    // Constructores
    public ProductosEntity() {}

    public ProductosEntity(int id, String nombre, int precio, int stock, boolean requiereReceta) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.requiereReceta = requiereReceta;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public boolean isRequiereReceta() {
        return requiereReceta;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setRequiereReceta(boolean requiereReceta) {
        this.requiereReceta = requiereReceta;
    }
}
