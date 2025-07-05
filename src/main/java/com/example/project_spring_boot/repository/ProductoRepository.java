package com.example.project_spring_boot.repository;

import com.example.project_spring_boot.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    boolean existsByNombreAndMarca(String nombre, String nombre1);

    boolean existsByNombreAndMarcaAndIdNot(String nombre, String marca, Long id);

    List<Producto> findByCantidad_disponibleLessThan(int cantidad);

    @Query("SELECT p FROM Producto p WHERE " +
            "(:marca IS NULL OR LOWER(p.marca) = LOWER(:marca)) AND " +
            "(:precioMin IS NULL OR p.costo >= :precioMin) AND " +
            "(:precioMax IS NULL OR p.costo <= :precioMax)")
    List<Producto> buscarProductos(
            @Param("marca") String marca,
            @Param("precioMin") Double precioMin,
            @Param("precioMax") Double precioMax);
}
