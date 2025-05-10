package com.tbd.DeliveryMedicamentos.controllers;

import com.tbd.DeliveryMedicamentos.entities.UsuarioEntity;
import com.tbd.DeliveryMedicamentos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> getAllUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioById(@PathVariable Integer id) {
        UsuarioEntity usuario = usuarioService.getUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioEntity> getUsuarioByEmail(@PathVariable String email) {
        UsuarioEntity usuario = usuarioService.getUsuarioByEmail(email);
        return usuario != null ?
                ResponseEntity.ok(usuario) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> createUsuario(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity nuevoUsuario = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> updateUsuario(@PathVariable Integer id,
                                                       @RequestBody UsuarioEntity usuario) {
        usuario.setId(id);
        UsuarioEntity usuarioActualizado = usuarioService.updateUsuario(usuario);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/iniciarSesion")
    public ResponseEntity<?> loginUsuario(@RequestParam String email, @RequestParam String password) {
        UsuarioEntity usuario = usuarioService.getUsuarioByEmail(email);

        if (usuario != null && usuario.getPassword().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("id", usuario.getId());
            response.put("tipo", usuario.getTipo());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body("Email o contraseña incorrectos.");
        }
    }

}