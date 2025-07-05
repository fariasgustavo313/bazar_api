package com.example.project_spring_boot.service;

import com.example.project_spring_boot.model.Cliente;
import com.example.project_spring_boot.model.Producto;
import com.example.project_spring_boot.model.Venta;
import com.example.project_spring_boot.repository.ClienteRepository;
import com.example.project_spring_boot.repository.ProductoRepository;
import com.example.project_spring_boot.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerVentasPorId(Long id){
        return ventaRepository.findById(id).orElse(null);
    }

    public void crearVenta(Venta venta){
        Venta ventaAux = new Venta();
        List<Producto> productos = productoRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();
        List<Producto> productosVenta = venta.getProductos();
        double total = productosVenta.stream().mapToDouble(Producto::getCosto).sum();

        if(!clientes.contains(venta.getCliente())){
            throw new RuntimeException("Cliente no encontrado");
        } else if(!productos.containsAll(productosVenta)){
            throw new RuntimeException("Producto no encontrado");
        }
        ventaAux.setTotal(total);
        ventaAux.setFecha_venta(LocalDate.now());
        ventaAux.setCliente(venta.getCliente());
        ventaAux.setProductos(productosVenta);
        ventaRepository.save(ventaAux);
    }

    public void eliminarVenta(Long id){
        ventaRepository.deleteById(id);
    }

    public void editarVenta(Long id, Venta venta){
        Venta ventaAux = ventaRepository.findById(id).orElse(null);
        ventaAux.setFecha_venta(venta.getFecha_venta());
        ventaAux.setCliente(venta.getCliente());
        ventaAux.setProductos(venta.getProductos());
        ventaAux.setTotal(venta.getTotal());
        ventaRepository.save(ventaAux);
    }
}
