package com.example.project_spring_boot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha_venta;
    private double total;
    private List<Producto> productos;
    private Cliente cliente;
}
