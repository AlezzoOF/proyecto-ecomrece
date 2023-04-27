package com.idos.hlg.apk.clientes.facturacion.services.contract;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Producto;

import java.util.List;

public interface IProductoService extends IGenricServices<Producto> {
    List<Producto> findAllByEnable();//Buscar los productos en stock

    List<Producto> findAllByTipo(String tipo);//Busca los producto segun tipo

    List<Producto> findByPrecioBetween(double precioMinimo, double precioMaximo);


}
