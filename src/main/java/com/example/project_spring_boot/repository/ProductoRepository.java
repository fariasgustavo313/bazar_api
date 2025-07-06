package com.example.project_spring_boot.repository;

import com.example.project_spring_boot.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    boolean existsByNombreAndMarca(String nombre, String marca);

    boolean existsByNombreAndMarcaAndIdNot(String nombre, String marca, Long id);

    List<Producto> findByCantidadDisponibleLessThan(int cantidadDisponible);

    @Query("SELECT p FROM Producto p WHERE " +
            "(:marca IS NULL OR LOWER(p.marca) = LOWER(:marca)) AND " +
            "(:precioMin IS NULL OR p.costo >= :precioMin) AND " +
            "(:precioMax IS NULL OR p.costo <= :precioMax)")
    List<Producto> buscarProductos(
            @Param("marca") String marca,
            @Param("precioMin") Double precioMin,
            @Param("precioMax") Double precioMax);

    @Query("SELECT p FROM Producto p WHERE p.eliminado = false")
    List<Producto> findAllActive();

    @Query("SELECT p FROM Producto p WHERE p.id = :id AND p.eliminado = false")
    Optional<Producto> findByIdActive(Long id);
}
