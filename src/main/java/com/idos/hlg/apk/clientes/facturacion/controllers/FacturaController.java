package com.idos.hlg.apk.clientes.facturacion.controllers;

import com.idos.hlg.apk.clientes.facturacion.models.dto.FacturaRequest;
import com.idos.hlg.apk.clientes.facturacion.models.entities.Factura;
import com.idos.hlg.apk.clientes.facturacion.models.entities.LineaFactura;
import com.idos.hlg.apk.clientes.facturacion.services.JwtService;
import com.idos.hlg.apk.clientes.facturacion.services.contract.IFacturaService;
import com.idos.hlg.apk.clientes.facturacion.services.contract.ILineaFacturaService;
import com.idos.hlg.apk.clientes.facturacion.services.contract.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
@RequiredArgsConstructor
public class FacturaController {
    private final IFacturaService service;
    private final JwtService jwtService;
    private final IUsuarioService iUsuarioService;
    private final ILineaFacturaService iLineaFacturaService;

    //Crear la factura
    @PostMapping("/crear")
    public ResponseEntity<String> addFactura(@RequestBody @NotNull FacturaRequest facturaRequest) {
        Factura factura = new Factura();
        factura.setUsuario(iUsuarioService.findIdByUsername(jwtService.extractUsername(facturaRequest.jwt())));
        for (LineaFactura l :
                facturaRequest.lista()) {
            l.setFactura(factura);
            factura.addLineaFactura(l);
            iLineaFacturaService.save(l);
        }
        service.save(factura);
        return ResponseEntity.ok("ala");

    }

    //Borrar la factura
    @DeleteMapping("/delete/{id}")
    public void deleteFactura(Long id) {
        service.deleteById(id);
    }
    //Calcular precio total factura
    //Calcular precio total + itp

    //Ver todas las facturas
    @GetMapping("/ver-todas")
    public List<Factura> mostrarTodas() {
        return service.findAll();
    }

    //Ver todas las facturas de un usuario
    @GetMapping("/ver-por-usuarios")
    public List<Factura> mostrarTodas(@RequestParam("jwt") String jwt) {

        return service.findAllByUsuario(iUsuarioService.findIdByUsername(jwtService.extractUsername(jwt)).getId());
    }

}
