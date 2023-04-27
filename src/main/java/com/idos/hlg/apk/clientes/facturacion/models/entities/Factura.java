package com.idos.hlg.apk.clientes.facturacion.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "facturas")
@Data
public class Factura {
    @Id
    @GeneratedValue
    @Column(name = "factura_id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

    private List<LineaFactura> lineasFacturas;


    public Factura() {
        lineasFacturas = new ArrayList<>();
        createAt = new Date(System.currentTimeMillis());
    }

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public void addLineaFactura(LineaFactura lineaFactura) {
        lineasFacturas.add(lineaFactura);
    }

}
