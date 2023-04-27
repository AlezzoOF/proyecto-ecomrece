package com.idos.hlg.apk.clientes.facturacion.models.entities.mapper;

import com.idos.hlg.apk.clientes.facturacion.models.dto.ProductoDTO;
import com.idos.hlg.apk.clientes.facturacion.models.entities.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoDTOInToProducto implements IMapper<ProductoDTO, Producto> {
    @Override
    public Producto map(ProductoDTO in) {

        Producto p = new Producto(in.nombre(), in.precio(), "prueba", in.tipo());

        return p;
    }
}
