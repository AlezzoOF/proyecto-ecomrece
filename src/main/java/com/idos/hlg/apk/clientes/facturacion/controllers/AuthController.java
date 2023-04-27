package com.idos.hlg.apk.clientes.facturacion.controllers;

import com.idos.hlg.apk.clientes.facturacion.models.dto.AuthenticationRequest;
import com.idos.hlg.apk.clientes.facturacion.models.dto.AuthenticationResponse;
import com.idos.hlg.apk.clientes.facturacion.models.dto.RegisterRequest;
import com.idos.hlg.apk.clientes.facturacion.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.auth(request));
    }


}
