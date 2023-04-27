package com.idos.hlg.apk.clientes.facturacion.repositories;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Long> {

    List<Factura> findAllByUsuario(Long id);
}
