package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.DTO.DetallePedidoDTO;
import com.tbd.DeliveryMedicamentos.DTO.ResumenPedidoClienteDTO;
import com.tbd.DeliveryMedicamentos.entities.PedidosEntity;
import com.tbd.DeliveryMedicamentos.repositories.PedidosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PedidosService {
    private final PedidosRepository pedidoRepository;

    public PedidosService(PedidosRepository repository) {
        this.pedidoRepository = repository;
    }

    public List<PedidosEntity> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidosEntity getPedidoById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public PedidosEntity createPedido(PedidosEntity pedido) {
        return pedidoRepository.save(pedido);
    }

    public void registrarPedido(PedidosEntity pedido, List<DetallePedidoDTO> detalles) {
        pedidoRepository.registrarPedido(pedido, detalles);
    }

    public List<ResumenPedidoClienteDTO> obtenerResumen() {
        return pedidoRepository.obtenerResumenPedidos();
    }

    public PedidosEntity updatePedido(PedidosEntity pedido) {
        pedidoRepository.update(pedido);
        return pedidoRepository.findById(pedido.getId());
    }

    public void deletePedido(Integer id) {
        pedidoRepository.delete(id);
    }

    public List<Map<String, Object>> tiempoPromedioPorRepartidor() {
        return pedidoRepository.tiempoPromedioPorRepartidor();
    }

    public List<Map<String, Object>> tiempoPromedioPorRepartidorId(Integer id) {
        return pedidoRepository.tiempoPromedioPorRepartidorId(id);
    }

    public void cambiarEstadoPedido(int idPedido, String nuevoEstado) {
        pedidoRepository.cambiarEstadoPedido(idPedido, nuevoEstado);
    }

    public Map<String, Object> medioPagoMasUsadoEnUrgencias() {
        return pedidoRepository.medioPagoMasUsadoEnUrgencias();
    }

    public long contarPedidosMesActual() {
        return pedidoRepository.countPedidosMesActual();
    }

    public List<Map<String, Object>> farmaciasConMasEntregasFallidas() {
        return pedidoRepository.farmaciasConMasEntregasFallidas();
    }

}