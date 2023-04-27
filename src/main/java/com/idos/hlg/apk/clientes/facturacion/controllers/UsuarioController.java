package com.idos.hlg.apk.clientes.facturacion.controllers;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Usuario;
import com.idos.hlg.apk.clientes.facturacion.services.impl.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl service;

    //agregar usuario

    @PostMapping("/guardar")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos del usuario no validos o vacios");
        } else {
            service.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado");
        }

    }
    //buscar usuario x username

    @GetMapping("/auth")
    public ResponseEntity<String> authUsuario(@RequestBody @NotNull String username) {
        if (username.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } else {
            service.findByUsername(username);
            return ResponseEntity.ok("");
        }
    }

    //borrar usuario(disable)
    @PutMapping("/disableUser/{id}")
    public ResponseEntity<String> disableUser(@PathVariable("id") Long id) {
        service.disableUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario borrado");
    }

    //enable usuario
    @PutMapping("/enableUser/{id}")
    public ResponseEntity<String> enableUser(@PathVariable("id") Long id) {
        service.enableUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario habilitado");
    }

    //buscar x id
    @GetMapping("/buscar/{id}")
    public Usuario buscar(@PathVariable("id") Long id) {

        return service.findById(id);
    }

    //ver todos los usuarios
    @GetMapping("/mostrar")
    public List<Usuario> mostrar() {
        return service.findAll();
    }

    // ver todos los usuarios borrados //ver todos los usuarios habilitados
    @GetMapping("/mostrar/{enable}")
    public List<Usuario> mostrarDisable(@PathVariable("enable") boolean enable) {
        return service.findEnable(enable);
    }


    //borrar usuario de la BD

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Borrado");
    }

}
