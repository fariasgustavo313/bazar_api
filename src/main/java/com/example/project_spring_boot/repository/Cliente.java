package com.example.project_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cliente extends JpaRepository<Cliente, Long> {
}
