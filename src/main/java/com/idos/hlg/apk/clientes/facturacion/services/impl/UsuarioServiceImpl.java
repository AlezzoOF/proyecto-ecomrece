package com.idos.hlg.apk.clientes.facturacion.services.impl;

import com.idos.hlg.apk.clientes.facturacion.models.dto.UsuarioResponse;
import com.idos.hlg.apk.clientes.facturacion.models.entities.Usuario;
import com.idos.hlg.apk.clientes.facturacion.repositories.IUsuarioRepository;
import com.idos.hlg.apk.clientes.facturacion.services.contract.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository repository;

    public UsuarioServiceImpl(IUsuarioRepository repository) {
        this.repository = repository;
    }

    //Buscar todos
    @Override
    public List<Usuario> findAll() {

        return repository.findAll();
    }

    //guardar usuario
    @Override
    public Usuario save(Usuario actividad) {

        return repository.save(actividad);
    }

    //buscar x id
    @Override
    public Usuario findById(Long id) {
        Optional<Usuario> a = repository.findById(id);
        Usuario user = a.get();

        return user;
    }

    //borrar x id
    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);
    }

    //borrar todo
    @Override
    public void deleteAll() {

        repository.deleteAll();

    }

    //buscar x nombre de usuario
    @Override
    public UsuarioResponse findByUsername(String email) {
        UsuarioResponse user = new UsuarioResponse(repository.findByEmail(email).get().getEmail(), repository.findByEmail(email).get().getPwd());

        return user;
    }

    //buscar id x nombre de usaurio
    @Override
    public Usuario findIdByUsername(String email) {

        return repository.findByEmail(email).get();
    }

    @Override
    public void enableUsuario(Long id) {
        repository.enableUsuario(id);
    }

    @Override
    public void disableUsuario(Long id) {
        repository.disableUsuario(id);

    }

    @Override
    public List<Usuario> findEnable(boolean enable) {
        return repository.findByEnable(enable);
    }
}
