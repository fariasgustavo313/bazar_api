package com.example.project_spring_boot.service;

import com.example.project_spring_boot.model.Venta;
import com.example.project_spring_boot.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerVentasPorId(Long id){
        return ventaRepository.findById(id).orElse(null);
    }

    public void crearVenta(Venta venta){
        ventaRepository.save(venta);
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
