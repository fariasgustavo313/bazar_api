package com.example.project_spring_boot.controller;

import com.example.project_spring_boot.dto.EstadisticasDTO;
import com.example.project_spring_boot.model.Venta;
import com.example.project_spring_boot.repository.ClienteRepository;
import com.example.project_spring_boot.repository.ProductoRepository;
import com.example.project_spring_boot.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping
    public EstadisticasDTO obtenerEstadisticasGenerales() {
        Long cantidad_productos = productoRepository.count();
        Long cantidad_ventas = clienteRepository.count();
        Long cantidad_clientes = ventaRepository.count();
        double total_recaudado = ventaRepository.findAll()
                .stream()
                .mapToDouble(Venta::getTotal)
                .sum();

        return new EstadisticasDTO(
                cantidad_productos,
                cantidad_clientes,
                cantidad_ventas,
                total_recaudado
        );
    }
}
