package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class RepartidorEntity {
    private int id;
    private String rut;
    private String nombre;
    private String telefono;

    // Constructores
    public RepartidorEntity() {}

    public RepartidorEntity(int id, String rut, String nombre, String telefono) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}