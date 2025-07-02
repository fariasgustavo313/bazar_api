package com.example.project_spring_boot.controller;

import com.example.project_spring_boot.model.Producto;
import com.example.project_spring_boot.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerProductos(){
        return productoService.obtenerProductos();
    }

    @GetMapping("{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    public void crearProducto(@RequestBody Producto producto){
        productoService.crearProducto(producto);
    }

    @DeleteMapping("{id}")
    public void eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
    }

    @PutMapping("{id}")
    public void actualizarProducto(@PathVariable Long id,
                                   @RequestBody Producto producto){
        productoService.actualizarProducto(id, producto);
    }
}
