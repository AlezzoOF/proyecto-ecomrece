package com.idos.hlg.apk.clientes.facturacion.repositories;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Producto;
import com.idos.hlg.apk.clientes.facturacion.models.entities.auxiliar.DescripcionProducto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAllByTipo(DescripcionProducto descripcionProducto);

    List<Producto> findByPrecioBetween(double precioMinimo, double precioMaximo);

    List<Producto> findByEnable(boolean bol);

    @Transactional
    @Modifying
    @Query("UPDATE Producto p SET p.enable = false WHERE p.id = ?1")
    void disableProducto(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Producto p SET p.enable = true WHERE p.id = ?1")
    void enableProducto(Long id);


}
