package com.example.project_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @NotNull(message = "La fecha de la venta es obligatoria")
    private LocalDate fecha_venta;

    @NotNull(message = "El total es obligatorio")
    private double total;

    @ManyToMany
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "venta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    @NotNull(message = "Debe asociar al menor un producto a la venta")
    private List<Producto> productos;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "Debe asociar un cliente a la venta")
    private Cliente cliente;
}
