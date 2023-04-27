package com.idos.hlg.apk.clientes.facturacion.models.dto;

import com.idos.hlg.apk.clientes.facturacion.models.entities.LineaFactura;

import java.util.List;

public record FacturaRequest(String jwt, List<LineaFactura> lista) {
}
