package com.example.project_spring_boot.controller;

import com.example.project_spring_boot.dto.VentaMayorDTO;
import com.example.project_spring_boot.model.Producto;
import com.example.project_spring_boot.model.Venta;
import com.example.project_spring_boot.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/productos/{id_venta}")
    public ResponseEntity<List<Producto>> obtenerProductosDeVenta(@PathVariable Long id_venta){
        List<Producto> productos = ventaService.obtenerProductosDeVenta(id_venta);
        if (productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/resumen/{fecha_venta}")
    public ResponseEntity<?> obtenerResumenVentasPorFecha(@PathVariable String fecha_venta){
        try {
            LocalDate fecha = LocalDate.parse(fecha_venta);
            Map<String, Object> resumen = ventaService.obtenerResumenVentasPorFecha(fecha);
            return ResponseEntity.ok(resumen);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Formato de fecha invalido. Utilizar yyy-MM-dd");
        }
    }

    @GetMapping("/mayor_venta")
    public ResponseEntity<?> obtenerMayorVenta() {
        try {
            VentaMayorDTO dto = ventaService.obtenerVentaConMayorMonto();
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
