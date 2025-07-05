package com.example.project_spring_boot.repository;

import com.example.project_spring_boot.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    boolean existsByNombreAndMarca(String nombre, String nombre1);

    boolean existsByNombreAndMarcaAndIdNot(String nombre, String marca, Long id);

    List<Producto> findByCantidad_disponibleLessThan(int cantidad);
}
