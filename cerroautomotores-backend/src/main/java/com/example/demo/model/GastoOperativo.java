package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "gastos_operativos_venta")
public class GastoOperativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gasto")
    private Long idGasto;

    // Relación con Venta (Muchos gastos pueden pertenecer a Una venta)
    // Esta es la parte "dueña" de la relación si Venta usa @OneToMany con @JoinColumn
    // O si Venta usa mappedBy, entonces esta es la que define el JoinColumn.
    // Por simplicidad, Venta gestionará la columna de unión.
    // Si quisiéramos una relación bidireccional explícita aquí:
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id_venta_fk", nullable = false)
    // private Venta venta;
    // Pero para una simple lista en Venta, no es estrictamente necesario el campo Venta aquí
    // si Venta va a usar @JoinColumn en su @OneToMany.
    // Por ahora, mantendremos GastoOperativo simple.
 // --- INICIO DE LA MODIFICACIÓN ---
    // Relación inversa con Venta (Muchos gastos pertenecen a Una venta)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta_fk", nullable = false) // Esta columna YA EXISTE en esta tabla
    @JsonBackReference                                           // y define la clave foránea a la tabla 'ventas'.
    private Venta venta;
    // --- FIN DE LA MODIFICACIÓN ---
    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, precision = 25, scale = 2) // <-- AUMENTAMOS LA PRECISIÓN AQUÍ
    private BigDecimal monto;
    @Column(name = "fecha_gasto")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaGasto;

    // Constructores, Getters y Setters
    public GastoOperativo() {}

    public Long getIdGasto() { return idGasto; }
    public void setIdGasto(Long idGasto) { this.idGasto = idGasto; }
    // public Venta getVenta() { return venta; } // Si tuvieras el campo Venta
    // public void setVenta(Venta venta) { this.venta = venta; } // Si tuvieras el campo Venta
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public LocalDate getFechaGasto() { return fechaGasto; }
    public void setFechaGasto(LocalDate fechaGasto) { this.fechaGasto = fechaGasto; }
    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

}