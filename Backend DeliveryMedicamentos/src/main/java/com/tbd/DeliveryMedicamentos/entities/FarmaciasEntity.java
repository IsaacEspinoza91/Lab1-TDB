package com.tbd.DeliveryMedicamentos.entities;

public class FarmaciasEntity {
    private int id;
    private String nombre;
    private String lugar;

    // Constructores
    public FarmaciasEntity() {}

    public FarmaciasEntity(int id, String nombre, String lugar) {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLugar() {
        return lugar;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

}
