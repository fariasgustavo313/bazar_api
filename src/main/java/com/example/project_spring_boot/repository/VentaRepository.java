package com.example.project_spring_boot.repository;

import com.example.project_spring_boot.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {

    @Query("SELECT v FROM Venta v WHERE v.cliente.dni = :dni")
    List<Venta> obtenerVentasPorDniCliente(@Param("dni") String dni);
}
