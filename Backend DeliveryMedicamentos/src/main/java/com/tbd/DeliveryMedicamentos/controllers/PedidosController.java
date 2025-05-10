package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.DTO.PedidoConDetallesDTO;
import com.tbd.DeliveryMedicamentos.DTO.ResumenPedidoClienteDTO;
import com.tbd.DeliveryMedicamentos.entities.PedidosEntity;
import com.tbd.DeliveryMedicamentos.services.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosController {
    private final PedidosService pedidoService;

    @Autowired
    public PedidosController(PedidosService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<PedidosEntity>> getAllPedidos() {
        List<PedidosEntity> pedidos = pedidoService.getAllPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidosEntity> getPedidoById(@PathVariable int id) {
        PedidosEntity pedido = pedidoService.getPedidoById(id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PedidosEntity> createPedido(@RequestBody PedidosEntity pedido) {
        PedidosEntity nuevoPedido = pedidoService.createPedido(pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @PostMapping("/registrar")
    public void registrarPedido(@RequestBody PedidoConDetallesDTO pedidoDTO) {
        pedidoService.registrarPedido(pedidoDTO.getPedido(), pedidoDTO.getDetalles());
    }

    @GetMapping("/resumen")
    public List<ResumenPedidoClienteDTO> getResumen() {
        return pedidoService.obtenerResumen();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidosEntity> updatePedido(@PathVariable int id, @RequestBody PedidosEntity pedido) {
        pedido.setId(id);
        PedidosEntity pedidoActualizado = pedidoService.updatePedido(pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable int id) {
        pedidoService.deletePedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tiempo-promedio-repartidor")
    public ResponseEntity<List<Map<String, Object>>> tiempoPromedioPorRepartidor() {
        return ResponseEntity.ok(pedidoService.tiempoPromedioPorRepartidor());
    }

    @GetMapping("/tiempo-promedio-repartidor/{id}")
    public ResponseEntity<List<Map<String, Object>>> tiempoPromedioPorRepartidorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoService.tiempoPromedioPorRepartidorId(id));
    }

}