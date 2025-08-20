package com.example.demo.repository;

import com.example.demo.model.DetalleFinanciacionPrendaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFinanciacionPrendariaRepository extends JpaRepository<DetalleFinanciacionPrendaria, Long> {
    // El ID (Long) aquí es el id_venta_fk
}