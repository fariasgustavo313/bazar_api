package com.example.project_spring_boot.repository;

import com.example.project_spring_boot.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDni(String dni);

    @Query("SELECT c FROM Cliente c WHERE c.eliminado = false")
    List<Cliente> findAllActive();

    @Query("SELECT c FROM Cliente c WHERE c.id = :id AND c.eliminado = false")
    Optional<Cliente> findByIdActive(Long id);
}
