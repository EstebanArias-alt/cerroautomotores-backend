package com.example.demo.repository;

import com.example.demo.model.Empleado; // Importa tu entidad Empleado
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Métodos CRUD básicos ya disponibles.
    // Puedes añadir consultas personalizadas si es necesario.
}