package com.tbd.DeliveryMedicamentos.security;

public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String tipo;

    // Constructores
    public RegisterRequest() {
    }

    public RegisterRequest(String nombre, String apellido, String email, String password, String tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}