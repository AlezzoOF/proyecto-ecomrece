package com.idos.hlg.apk.clientes.facturacion.services.contract;

import java.util.List;

public interface IGenricServices<E> {
    List<E> findAll();

    E save(E actividad);

    E findById(Long id);

    void deleteById(Long id);

    void deleteAll();
}
