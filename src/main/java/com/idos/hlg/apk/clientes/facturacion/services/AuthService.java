package com.idos.hlg.apk.clientes.facturacion.services;

import com.idos.hlg.apk.clientes.facturacion.models.dto.AuthenticationRequest;
import com.idos.hlg.apk.clientes.facturacion.models.dto.AuthenticationResponse;
import com.idos.hlg.apk.clientes.facturacion.models.dto.RegisterRequest;
import com.idos.hlg.apk.clientes.facturacion.models.entities.Usuario;
import com.idos.hlg.apk.clientes.facturacion.models.entities.mapper.RegisterRequestInToUser;
import com.idos.hlg.apk.clientes.facturacion.repositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final IUsuarioRepository repository;
    private final RegisterRequestInToUser mapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Usuario u = mapper.map(request);
        repository.save(u);
        String jwt = jwtService.generateToken(u);
        return new AuthenticationResponse(jwt);
    }

    public AuthenticationResponse auth(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPwd()));
        Usuario u = repository.findByEmail(request.getEmail()).orElseThrow();//manejar la excepcion
        String jwt = jwtService.generateToken(u);
        return new AuthenticationResponse(jwt);
    }
}
