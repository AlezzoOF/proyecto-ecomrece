package com.idos.hlg.apk.clientes.facturacion.services.impl;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Factura;
import com.idos.hlg.apk.clientes.facturacion.models.entities.LineaFactura;
import com.idos.hlg.apk.clientes.facturacion.repositories.IFacturaRepository;
import com.idos.hlg.apk.clientes.facturacion.repositories.ILineaFacturaRepository;
import com.idos.hlg.apk.clientes.facturacion.services.contract.IFacturaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImp implements IFacturaService {

    private final IFacturaRepository repository;
    private final ILineaFacturaRepository iLineaFacturaRepository;

    public FacturaServiceImp(IFacturaRepository repository, ILineaFacturaRepository iLineaFacturaRepository) {
        this.repository = repository;
        this.iLineaFacturaRepository = iLineaFacturaRepository;
    }

    //Buscar todas las facturas
    @Override
    public List<Factura> findAll() {
        return repository.findAll();
    }

    //Buscar facturas x usuario
    @Override
    public List<Factura> findAllByUsuario(Long id) {
        return repository.findAllByUsuario(id);
    }

    // crear factura
    @Override
    public Factura save(Factura actividad) {
        return repository.save(actividad);
    }

    //Buscar factura por id
    @Override
    public Factura findById(Long id) {
        return repository.findById(id).get();
    }

    //Borrar factura x id
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    //Borrar todo
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }


    //Calcular precio total
    @Override
    public double calcPrecioTotal(Long id) {

        List<LineaFactura> list = iLineaFacturaRepository.findAllByFactura_id(id);
        double total = 0;

        for (LineaFactura l :
                list) {

            total += l.calcularPrecio();

        }
        return total;

    }
}
