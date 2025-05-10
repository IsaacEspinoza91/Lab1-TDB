package com.tbd.DeliveryMedicamentos.services;

import com.tbd.DeliveryMedicamentos.DTO.DetallePedidoDTO;
import com.tbd.DeliveryMedicamentos.entities.PedidosEntity;
import com.tbd.DeliveryMedicamentos.repositories.PedidosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public PedidosEntity updatePedido(PedidosEntity pedido) {
        pedidoRepository.update(pedido);
        return pedidoRepository.findById(pedido.getId());
    }

    public void deletePedido(Integer id) {
        pedidoRepository.delete(id);
    }
}