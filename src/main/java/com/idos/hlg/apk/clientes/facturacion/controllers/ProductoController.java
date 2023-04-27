package com.idos.hlg.apk.clientes.facturacion.controllers;

import com.idos.hlg.apk.clientes.facturacion.models.entities.Producto;
import com.idos.hlg.apk.clientes.facturacion.models.entities.auxiliar.DescripcionProducto;
import com.idos.hlg.apk.clientes.facturacion.services.impl.ProductoServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/producto")
public class ProductoController {
    private final ProductoServiceImp service;


    //Entrada para la creacion de un producto
    @PostMapping("/agregar-img")
    public ResponseEntity<String> agregarProductoImg(@RequestParam("imagen") MultipartFile file,
                                                     @RequestParam("nombre") String nombre,
                                                     @RequestParam("precio") double precio,
                                                     @RequestParam("tipo") String tipo) {
        if (nombre == null || precio == 0 || tipo == null) {
            return ResponseEntity.badRequest().body("El producto no tiene los datos necesarios");
        }

        try {
            // Guardar la imagen en el servidor
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path path = Paths.get("./uploads");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            String fileUrl = path.toAbsolutePath() + "/" + filename;
            Files.copy(file.getInputStream(), path.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

            // Crear y guardar el objeto Producto
            DescripcionProducto tipoProducto = DescripcionProducto.valueOf(tipo);
            Producto producto = new Producto(nombre, precio, fileUrl, tipoProducto);
            service.save(producto);
            return ResponseEntity.ok("El producto se guardó correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
        }

    }


    //Eliminar un producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("El producto se elimino correctamente");
    }


    //Visualizacion de los productos
    @GetMapping("/mostrar")
    public List<Producto> mostrarAll() {
        return service.findAll();
    }


    //Filtro enable
    @GetMapping("/mostrar/enable")
    public List<Producto> mostrarAllEnable() {
        return service.findAllByEnable();
    }


    //Filtro tipo
    @GetMapping("/mostrar/{tipo}")
    public List<Producto> mostrarAllTipo(@PathVariable String tipo) {
        return service.findAllByTipo(tipo);
    }


    //filtro precio
    @GetMapping("/mostrar/{precioMinimo}/{precioMax}")
    public List<Producto> mostrarAllFiltroPrecio(@PathVariable double precioMinimo, @PathVariable double precioMax) {

        return service.findByPrecioBetween(precioMinimo, precioMax);
    }


    //cambiar el campo enable
    @PutMapping("/desabilitar/{id}")
    public ResponseEntity<String> cambiarEnable(@PathVariable Long id) {
        service.cambiarEnable(id);
        return ResponseEntity.ok("El producto se desabilito correctamente");
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<String> cambiarToEnable(@PathVariable Long id) {
        service.cambiarToEnable(id);
        return ResponseEntity.ok("El producto se habilito correctamente");
    }


    //mostrar un producto por el id
    @GetMapping("/mostrar/uno/{id}")
    public Producto mostrarUno(@PathVariable Long id) {
        return service.findById(id);
    }

}




