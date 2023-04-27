package com.idos.hlg.apk.clientes.facturacion.models.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "lineas_factura")
@Data
public class LineaFactura {
    @Id
    @GeneratedValue
    @Column(name = "linea_factura_id")
    private Long id;
    private int cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    public double calcularPrecio() {

        return producto.getPrecio() * cantidad;
    }


}
