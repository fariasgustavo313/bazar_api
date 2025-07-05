package com.example.project_spring_boot.controller;

import com.example.project_spring_boot.model.Producto;
import com.example.project_spring_boot.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos(){
        List<Producto> productos = productoService.obtenerProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id){
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID: " + id);
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto){
        try {
            productoService.crearProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id,
                                                @Valid @RequestBody Producto producto){
        try {
            productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok("Producto actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/falta_stock/{cantidad}")
    public ResponseEntity<List<Producto>> obtenerProductosConStockMenosA(@PathVariable int cantidad) {
        List<Producto> productos = productoService.obtenerProductosConStockMenosA(cantidad);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
}
