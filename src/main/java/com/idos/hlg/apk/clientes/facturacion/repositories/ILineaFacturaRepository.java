package com.idos.hlg.apk.clientes.facturacion.repositories;

import com.idos.hlg.apk.clientes.facturacion.models.entities.LineaFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILineaFacturaRepository extends JpaRepository<LineaFactura, Long> {
    List<LineaFactura> findAllByFactura_id(Long id);
}
