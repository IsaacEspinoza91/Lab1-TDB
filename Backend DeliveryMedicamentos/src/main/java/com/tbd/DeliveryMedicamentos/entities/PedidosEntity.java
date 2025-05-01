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

    // Constructores, Getters, Setters

}
