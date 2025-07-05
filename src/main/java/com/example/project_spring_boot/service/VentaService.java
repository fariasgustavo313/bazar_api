package com.example.project_spring_boot.service;

import com.example.project_spring_boot.dto.VentaMayorDTO;
import com.example.project_spring_boot.model.Cliente;
import com.example.project_spring_boot.model.Producto;
import com.example.project_spring_boot.model.Venta;
import com.example.project_spring_boot.repository.ClienteRepository;
import com.example.project_spring_boot.repository.ProductoRepository;
import com.example.project_spring_boot.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    public void crearVenta(Venta venta){
        Long clienteId = venta.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        List<Long> idsProductos = venta.getProductos()
                .stream()
                .map(Producto::getId)
                .collect(Collectors.toList());

        List<Producto> productos = productoRepository.findAllById(idsProductos);

        if (productos.size() != idsProductos.size()) {
            throw new RuntimeException("Uno o mas productos no fueron encontrados");
        }

        double total = productos.stream()
                .mapToDouble(Producto::getCosto)
                .sum();

        venta.setCliente(cliente);
        venta.setProductos(productos);
        venta.setFecha_venta(LocalDate.now());
        venta.setTotal(total);

        ventaRepository.save(venta);
    }

    public void eliminarVenta(Long id){
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("No se encontro la venta con ID: " + id);
        }
        ventaRepository.deleteById(id);
    }

    public void editarVenta(Long id, Venta venta){
        Venta ventaExistente = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrado con ID: " + id));

        Long clienteId = venta.getCliente().getId();
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        List<Long> idsProductos = venta.getProductos()
                .stream()
                .map(Producto::getId)
                .collect(Collectors.toList());

        List<Producto> productos = productoRepository.findAllById(idsProductos);

        if (productos.size() != idsProductos.size()) {
            throw new RuntimeException("Uno o mas productos no fueron encontrados");
        }

        double total = productos.stream()
                .mapToDouble(Producto::getCosto)
                .sum();

        ventaExistente.setCliente(cliente);
        ventaExistente.setProductos(productos);
        ventaExistente.setFecha_venta(LocalDate.now());
        ventaExistente.setTotal(total);
        ventaRepository.save(ventaExistente);
    }

    public List<Producto> obtenerProductosDeVenta(Long id_venta) {
        Venta venta = ventaRepository.findById(id_venta).orElseThrow(() -> new RuntimeException("Venta no encontrado con ID: " + id_venta));
        return venta.getProductos();
    }

    public Map<String, Object> obtenerResumenVentasPorFecha(LocalDate fechaVenta) {
        List<Venta> ventas = ventaRepository.findAll()
                .stream()
                .filter(venta -> venta.getFecha_venta().equals(fechaVenta))
                .toList();

        double sumaTotal = ventas.stream().mapToDouble(Venta::getTotal).sum();
        int cantidadVentas = ventas.size();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("fecha_venta", fechaVenta);
        respuesta.put("cantidad_ventas", cantidadVentas);
        respuesta.put("total", sumaTotal);

        return respuesta;
    }

    public VentaMayorDTO obtenerVentaConMayorMonto() {
        List<Venta> ventas = ventaRepository.findAll();

        if (ventas.isEmpty()) {
            throw new RuntimeException("No hay ventas registradas");
        }

        Venta ventaMax = ventas.stream()
                .max(Comparator.comparingDouble(Venta::getTotal))
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar la venta con monto mas alto"));

        VentaMayorDTO dto = new VentaMayorDTO();
        dto.setCodigo_venta(ventaMax.getId());
        dto.setTotal(ventaMax.getTotal());
        dto.setCantidad_productos(ventaMax.getProductos().size());
        dto.setNombre_cliente(ventaMax.getCliente().getNombre());
        dto.setApellido_cliente(ventaMax.getCliente().getApellido());

        return dto;
    }

    public List<Venta> obtenerVentasPorDniCliente(String dni) {
        return ventaRepository.obtenerVentasPorDniCliente(dni);
    }
}
