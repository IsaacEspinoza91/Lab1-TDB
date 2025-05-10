package com.tbd.DeliveryMedicamentos.entities;

public class UsuarioEntity {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private String tipo; // CLIENTE, REPARTIDOR, ADMIN

    // Constructores
    public UsuarioEntity() {}

    public UsuarioEntity(int id, String rut, String nombre, String apellido, String email,
                         String password, String telefono, String tipo) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }








    // Construccion del builder
    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    public static class UsuarioBuilder {
        private int id;
        private String rut;
        private String nombre;
        private String apellido;
        private String email;
        private String password;
        private String telefono;
        private String tipo;

        public UsuarioBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UsuarioBuilder rut(String rut) {
            this.rut = rut;
            return this;
        }

        public UsuarioBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public UsuarioBuilder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public UsuarioBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UsuarioBuilder password(String password) {
            this.password = password;
            return this;
        }
        public UsuarioBuilder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public UsuarioBuilder tipo(String tipo) {
            this.tipo = tipo;
            return this;
        }


        public UsuarioEntity build() {
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setId(id);
            usuario.setRut(rut);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setTelefono(telefono);
            usuario.setTipo(tipo);
            return usuario;
        }
    }

}