package com.idos.hlg.apk.clientes.facturacion.services.contract;

import com.idos.hlg.apk.clientes.facturacion.models.dto.UsuarioResponse;
import com.idos.hlg.apk.clientes.facturacion.models.entities.Usuario;

import java.util.List;

public interface IUsuarioService extends IGenricServices<Usuario> {
    UsuarioResponse findByUsername(String email);

    void enableUsuario(Long id);

    void disableUsuario(Long id);

    Usuario findIdByUsername(String email);

    List<Usuario> findEnable(boolean enable);

}
