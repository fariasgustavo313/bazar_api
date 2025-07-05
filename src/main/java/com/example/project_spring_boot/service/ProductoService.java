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
        validarProducto(producto);

        boolean existe = productoRepository.existsByNombreAndMarca(producto.getNombre(), producto.getMarca());
        if (existe) {
            throw new RuntimeException("Ya existe un producto con el mismo nombre y marca");
        }
        productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public void actualizarProducto(Long id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("El producto no existe"));
        validarProducto(producto);

        boolean existe = productoRepository.existsByNombreAndMarcaAndIdNot(producto.getNombre(), producto.getMarca(), id);
        if (existe) {
            throw new RuntimeException("Ya existe un producto con el mismo nombre y marca");
        }
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setMarca(producto.getMarca());
        productoExistente.setCantidad_disponible(producto.getCantidad_disponible());
        productoExistente.setCosto(producto.getCosto());
        productoRepository.save(productoExistente);
    }

    public List<Producto> obtenerProductosConStockMenosA(int cantidad) {
        return productoRepository.findByCantidad_disponibleLessThan(cantidad);
    }

    private void validarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio");
        }
        if (producto.getMarca() == null || producto.getMarca().isBlank()) {
            throw new RuntimeException("La marca es obligatoria");
        }
        if (producto.getCosto() <= 0) {
            throw new RuntimeException("El costo debe ser mayor a 0");
        }
        if (producto.getCantidad_disponible() < 0) {
            throw new RuntimeException("La cantidad disponible no puede ser negativa");
        }
    }
}
