package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class CalificacionesEntity {

    private int id;
    private String puntuacion;
    private int estrellas;
    private int clienteId;
    private int repartidorId;

    // Constructores
    public CalificacionesEntity() {}

    public CalificacionesEntity(int id, String puntuacion, int estrellas, int clienteId, int repartidorId) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.estrellas = estrellas;
        this.clienteId = clienteId;
        this.repartidorId = repartidorId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getRepartidorId() {
        return repartidorId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setRepartidorId(int repartidorId) {
        this.repartidorId = repartidorId;
    }
}
