package com.idos.hlg.apk.clientes.facturacion.models.entities.mapper;

import com.idos.hlg.apk.clientes.facturacion.models.dto.RegisterRequest;
import com.idos.hlg.apk.clientes.facturacion.models.entities.Usuario;
import com.idos.hlg.apk.clientes.facturacion.models.entities.auxiliar.Rol;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterRequestInToUser implements IMapper<RegisterRequest, Usuario> {
    private final PasswordEncoder passwordEncoder;

    public RegisterRequestInToUser(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario map(RegisterRequest in) {
        Usuario u = new Usuario();
        u.setNombre(in.getNombre());
        u.setApellido(in.getApellido());
        u.setEmail(in.getEmail());
        u.setPwd(passwordEncoder.encode(in.getPwd()));
        u.setRol(Rol.User);

        return u;
    }
}
