package com.example.project_spring_boot.service;

import com.example.project_spring_boot.model.Producto;
import com.example.project_spring_boot.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void crearProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public void actualizarProducto(Long id, Producto producto) {
        Producto productoAux = productoRepository.findById(id).orElse(null);
        productoAux.setNombre(producto.getNombre());
        productoAux.setMarca(producto.getMarca());
        productoAux.setCantidad_disponible(producto.getCantidad_disponible());
        productoAux.setCosto(producto.getCosto());
        productoRepository.save(productoAux);
    }
}
