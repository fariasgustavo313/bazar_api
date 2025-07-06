package com.example.project_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clientes", uniqueConstraints = {
        @UniqueConstraint(columnNames = "dni")
})
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    @Size(max = 50, message = "El apellido no puede superar los 50 caracteres")
    private String apellido;

    @NotBlank(message = "El DNI no puede estar vacio")
    @Size(max = 20, message = "El DNI no puede superar los 20 caracteres")
    @Column(unique = true)
    private String dni;

    @Column(name = "eliminado")
    private boolean eliminado = false;
}
