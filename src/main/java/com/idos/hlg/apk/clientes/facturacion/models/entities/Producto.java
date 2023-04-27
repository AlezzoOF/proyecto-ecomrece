package com.idos.hlg.apk.clientes.facturacion.models.entities;

import com.idos.hlg.apk.clientes.facturacion.models.entities.auxiliar.DescripcionProducto;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "productos")
@Data
public class Producto {
    @Id
    @GeneratedValue
    @Column(name = "producto_id")
    private Long id;

    private String nombre;

    private double precio;
    private String foto;
    @Enumerated(EnumType.STRING)
    private DescripcionProducto tipo;
    private boolean enable;

    public Producto() {

    }

    public Producto(String nombre, double precio, String foto, DescripcionProducto tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.foto = foto;
        this.tipo = tipo;

        enable = true;
    }
}
