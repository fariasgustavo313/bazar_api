package com.example.project_spring_boot.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentaMayorDTO {

    private Long codigo_venta;
    private double total;
    private int cantidad_productos;
    private String nombre_cliente;
    private String apellido_cliente;
}
