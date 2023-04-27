package com.idos.hlg.apk.clientes.facturacion.models.dto;

import com.idos.hlg.apk.clientes.facturacion.models.entities.auxiliar.DescripcionProducto;


public record ProductoDTO(String nombre, double precio, DescripcionProducto tipo) {
}
