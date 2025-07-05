package com.example.project_spring_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstadisticasDTO {

    private Long cantidad_productos;
    private Long cantidad_clientes;
    private Long cantidad_ventas;
    private double total_recaudado;
}
