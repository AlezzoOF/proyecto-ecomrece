package com.idos.hlg.apk.clientes.facturacion.services.contract;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Factura;

import java.util.List;

public interface IFacturaService extends IGenricServices<Factura> {

    List<Factura> findAllByUsuario(Long id);


    double calcPrecioTotal(Long id);


}
