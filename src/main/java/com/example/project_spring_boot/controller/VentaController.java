package com.example.project_spring_boot.controller;

import com.example.project_spring_boot.model.Venta;
import com.example.project_spring_boot.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> obtenerVentas(){
        return ventaService.obtenerVentas();
    }

    @GetMapping("/{id}")
    public Venta obtenerVentaPorId(@PathVariable Long id){
        return ventaService.obtenerVentasPorId(id);
    }

    @PostMapping
    public void crearVenta(@RequestBody Venta venta){
        ventaService.crearVenta(venta);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id){
        ventaService.eliminarVenta(id);
    }

    @PutMapping("/{id}")
    public void editarVenta(@PathVariable Long id,
                            @RequestBody Venta venta){
        ventaService.editarVenta(id, venta);
    }
}
