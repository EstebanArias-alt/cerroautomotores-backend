package com.example.demo.repository;

import com.example.demo.model.Cliente; // Importa tu entidad Cliente

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // Opcional, pero buena práctica para claridad

@Repository // Indica que es un componente de repositorio gestionado por Spring
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // JpaRepository<TipoDeEntidad, TipoDeLaClavePrimaria>
	 Optional<Cliente> findByDniCuit(String dniCuit);
    // Spring Data JPA generará automáticamente los métodos CRUD básicos:
    // save(), findById(), findAll(), deleteById(), count(), existsById(), etc.

    // Puedes añadir métodos de consulta personalizados aquí si los necesitas más adelante.
    // Por ejemplo: Optional<Cliente> findByDniCuit(String dniCuit);
}