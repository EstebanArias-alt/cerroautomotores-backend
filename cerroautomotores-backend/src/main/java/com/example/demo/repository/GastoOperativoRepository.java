package com.example.demo.repository;

import com.example.demo.model.GastoOperativo; // Importa tu entidad GastoOperativo
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoOperativoRepository extends JpaRepository<GastoOperativo, Long> {
    // Para encontrar todos los gastos de una venta específica
    List<GastoOperativo> findByVenta_IdVenta(Long idVenta); // Asume que GastoOperativo tiene un campo 'venta'
                                                          // Si Venta tiene @OneToMany @JoinColumn(name="id_venta_fk")
                                                          // y GastoOperativo no tiene referencia a Venta,
                                                          // esta query no funcionará directamente así.
                                                          // Hibernate gestionará la relación. Si necesitas
                                                          // buscar gastos por idVenta, podrías necesitar
                                                          // una query nativa o JPQL, o añadir la relación
                                                          // @ManyToOne en GastoOperativo.
                                                          // Por ahora, dejemos el repositorio básico.
}