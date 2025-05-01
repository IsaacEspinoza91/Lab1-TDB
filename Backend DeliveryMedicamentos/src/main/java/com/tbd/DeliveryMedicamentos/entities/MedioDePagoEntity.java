package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class MedioDePagoEntity {

    private int id;
    private String tipo;

    // Constructores
    public MedioDePagoEntity() {}

    public MedioDePagoEntity(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
