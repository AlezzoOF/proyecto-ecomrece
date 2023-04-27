package com.idos.hlg.apk.clientes.facturacion.services.impl;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Producto;
import com.idos.hlg.apk.clientes.facturacion.models.entities.auxiliar.DescripcionProducto;
import com.idos.hlg.apk.clientes.facturacion.repositories.IProductoRepository;
import com.idos.hlg.apk.clientes.facturacion.services.contract.IProductoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductoServiceImp implements IProductoService {

    private final IProductoRepository repository;//inyeccion del repositorio de Producto

    public ProductoServiceImp(IProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    } // devuelve todos los productos en la base de datos

    @Override
    public Producto save(Producto actividad) {
        return repository.save(actividad);
    }

    @Override
    public Producto findById(Long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {

        repository.deleteById(id);

    }//eliminar un producto segun el id

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Producto> findAllByEnable() {//muestra los productos filtrados x existencia
        List<Producto> a = repository.findAll();
        List<Producto> aux = new ArrayList<>();
        for (Producto p :
                a) {
            if (p.isEnable()) {
                aux.add(p);
            }
        }
        return aux;
    }

    @Override
    public List<Producto> findAllByTipo(String tipo) { //filtra segun tipo
        DescripcionProducto tipoProducto = DescripcionProducto.valueOf(tipo);
        return repository.findAllByTipo(tipoProducto);
    }

    @Override
    public List<Producto> findByPrecioBetween(double precioMinimo, double precioMaximo) {
        return repository.findByPrecioBetween(precioMinimo, precioMaximo);
    }

    public void cambiarEnable(Long id) {
        repository.disableProducto(id);
//
    }

    public void cambiarToEnable(Long id) {
        repository.enableProducto(id);
    }

}
