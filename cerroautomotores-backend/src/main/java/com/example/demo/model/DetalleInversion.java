package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_inversion")
public class DetalleInversion {

    @Id
    @Column(name = "id_venta_fk")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_venta_fk")
    @JsonBackReference(value="venta-inversion") // Nombre único para la referencia
    private Venta venta;

    @Column(length = 50)
    private String codigo;

    @Column(name = "anio")
    private Integer anio;

    @Column(precision = 12, scale = 2)
    private BigDecimal precio;

    @Column
    private Integer cuotas;

    // --- Constructores, Getters y Setters ---
    public DetalleInversion() {}

    // No olvides generar todos los getters y setters
    // (Eclipse: Source > Generate Getters and Setters...)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Integer getCuotas() { return cuotas; }
    public void setCuotas(Integer cuotas) { this.cuotas = cuotas; }
}
