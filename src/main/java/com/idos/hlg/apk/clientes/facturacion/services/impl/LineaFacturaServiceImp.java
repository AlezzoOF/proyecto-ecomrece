package com.idos.hlg.apk.clientes.facturacion.services.impl;

import com.idos.hlg.apk.clientes.facturacion.models.entities.LineaFactura;
import com.idos.hlg.apk.clientes.facturacion.repositories.ILineaFacturaRepository;
import com.idos.hlg.apk.clientes.facturacion.services.contract.ILineaFacturaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineaFacturaServiceImp implements ILineaFacturaService {
    private final ILineaFacturaRepository repository;

    public LineaFacturaServiceImp(ILineaFacturaRepository repository) {
        this.repository = repository;
    }

    //Buscar todos
    @Override
    public List<LineaFactura> findAll() {
        return repository.findAll();
    }

    //Guardar
    @Override
    public LineaFactura save(LineaFactura actividad) {
        return repository.save(actividad);
    }

    //Buscar x id
    @Override
    public LineaFactura findById(Long id) {
        return repository.findById(id).get();
    }

    //Borrar x id
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    //borrar todo
    @Override
    public void deleteAll() {
        repository.deleteAll();

    }


}
