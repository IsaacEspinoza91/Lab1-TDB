package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class ClienteEntity {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private String telefono;

    // Constructores
    public ClienteEntity() {}

    public ClienteEntity(String rut, String nombre, String apellido, String direccion, String email, String telefono) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    // ... (otros getters y setters)

    // Métodos para operaciones CRUD
    public void insertar() {
        String sql = "INSERT INTO clientes (rut, nombre, apellido, direccion, email, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        // Ejecutar consulta con JDBC
    }

    public static ClienteEntity buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        // Ejecutar consulta y mapear resultados
        return null; // Retornar objeto Cliente
    }

    // Otros métodos según necesidad
}