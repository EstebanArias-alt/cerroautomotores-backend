package com.example.demo.repository;

import com.example.demo.model.DetalleFinanciacionPropia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFinanciacionPropiaRepository extends JpaRepository<DetalleFinanciacionPropia, Long> {
    // El ID (Long) aquí es el id_venta_fk
}