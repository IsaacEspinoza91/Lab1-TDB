package com.tbd.DeliveryMedicamentos.entities;

import java.sql.Date;
import java.util.List;

public class PedidosEntity {
    private int id;
    private Date fecha;
    private boolean urgencia;
    private int totalPagado;
    private String estadoEntrega;
    private Date fechaEntrega;
    private int clienteId;
    private int medioPagoId;

    // Constructores
    public PedidosEntity() {}

    public PedidosEntity(int id, Date fecha, boolean urgencia, int totalPagado,
                         String estadoEntrega, Date fechaEntrega, int clienteId, int medioPagoId) {
        this.id = id;
        this.fecha = fecha;
        this.urgencia = urgencia;
        this.totalPagado = totalPagado;
        this.estadoEntrega = estadoEntrega;
        this.fechaEntrega = fechaEntrega;
        this.clienteId = clienteId;
        this.medioPagoId = medioPagoId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public boolean isUrgencia() {
        return urgencia;
    }

    public int getTotalPagado() {
        return totalPagado;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getMedioPagoId() {
        return medioPagoId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setUrgencia(boolean urgencia) {
        this.urgencia = urgencia;
    }

    public void setTotalPagado(int totalPagado) {
        this.totalPagado = totalPagado;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setMedioPagoId(int medioPagoId) {
        this.medioPagoId = medioPagoId;
    }


}
