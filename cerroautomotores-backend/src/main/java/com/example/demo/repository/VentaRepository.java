package com.example.demo.repository;

import com.example.demo.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    /**
     * Busca ventas donde el nombre del cliente (ignorando mayúsculas/minúsculas)
     * o el DNI/CUIT del cliente contengan el término de búsqueda.
     * @param searchTerm El texto a buscar.
     * @return Una lista de ventas que coinciden.
     */
    @Query("SELECT v FROM Venta v JOIN v.cliente c WHERE " +
           "LOWER(c.nombreRazonSocial) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "c.dniCuit LIKE CONCAT('%', :searchTerm, '%')")
    List<Venta> searchByCliente(@Param("searchTerm") String searchTerm);

}
