package com.example.demo.model;

import jakarta.persistence.*; // o javax.persistence.*
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "detalles_financiacion_propia")
public class DetalleFinanciacionPropia {

    @Id
    // No usamos @GeneratedValue aquí porque el ID vendrá de la Venta asociada.
    @Column(name = "id_venta_fk")
    private Long id; // Este será el mismo ID que Venta.idVenta

    // Relación OneToOne bidireccional con Venta.
    // Venta es la dueña de la relación si usamos mappedBy en Venta.
    // Aquí, @MapsId le dice a JPA que la propiedad 'id' obtiene su valor
    // de la clave primaria de la entidad 'venta' asociada.
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Especifica que la PK de esta entidad es también una FK y se mapea desde la relación.
    @JoinColumn(name = "id_venta_fk")
    @JsonBackReference// Nombre de la columna en esta tabla que es PK y FK.
    private Venta venta;

    @Column(name = "monto_financiado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoFinanciado;

    @Column(name = "cantidad_cuotas", nullable = false)
    private Integer cantidadCuotas; // Usamos Integer para números enteros

    @Column(name = "valor_cuota_aproximado", precision = 10, scale = 2)
    private BigDecimal valorCuotaAproximado;

    @Column(name = "tasa_interes_nominal_anual", precision = 5, scale = 2)
    private BigDecimal tasaInteresNominalAnual;

    @Column(name = "costo_financiero_total", precision = 5, scale = 2)
    private BigDecimal costoFinancieroTotal;

    @Column(name = "anticipo_recibido", precision = 12, scale = 2)
    private BigDecimal anticipoRecibido;

    @Lob
    @Column(name = "condiciones_adicionales", columnDefinition = "TEXT")
    private String condicionesAdicionales;
    
    @Column(name = "monto_total_con_interes", precision = 12, scale = 2)
    private BigDecimal montoTotalConInteres;

    @Column(name = "valor_cuota", precision = 10, scale = 2)
    private BigDecimal valorCuota;
    
    @Column(name = "cantidad_cuotas_pagadas")
    private Integer cantidadCuotasPagadas;

    

	public Integer getCantidadCuotasPagadas() {
		return cantidadCuotasPagadas;
	}

	public void setCantidadCuotasPagadas(Integer cantidadCuotasPagadas) {
		this.cantidadCuotasPagadas = cantidadCuotasPagadas;
	}

	public BigDecimal getMontoTotalConInteres() {
		return montoTotalConInteres;
	}

	public void setMontoTotalConInteres(BigDecimal montoTotalConInteres) {
		this.montoTotalConInteres = montoTotalConInteres;
	}

	public BigDecimal getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}

	// --- Constructores ---
    public DetalleFinanciacionPropia() {
    }

    // --- Getters y Setters ---
    // (Generar con Eclipse o usar Lombok)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }
    public BigDecimal getMontoFinanciado() { return montoFinanciado; }
    public void setMontoFinanciado(BigDecimal montoFinanciado) { this.montoFinanciado = montoFinanciado; }
    public Integer getCantidadCuotas() { return cantidadCuotas; }
    public void setCantidadCuotas(Integer cantidadCuotas) { this.cantidadCuotas = cantidadCuotas; }
    public BigDecimal getValorCuotaAproximado() { return valorCuotaAproximado; }
    public void setValorCuotaAproximado(BigDecimal valorCuotaAproximado) { this.valorCuotaAproximado = valorCuotaAproximado; }
    public BigDecimal getTasaInteresNominalAnual() { return tasaInteresNominalAnual; }
    public void setTasaInteresNominalAnual(BigDecimal tasaInteresNominalAnual) { this.tasaInteresNominalAnual = tasaInteresNominalAnual; }
    public BigDecimal getCostoFinancieroTotal() { return costoFinancieroTotal; }
    public void setCostoFinancieroTotal(BigDecimal costoFinancieroTotal) { this.costoFinancieroTotal = costoFinancieroTotal; }
    public BigDecimal getAnticipoRecibido() { return anticipoRecibido; }
    public void setAnticipoRecibido(BigDecimal anticipoRecibido) { this.anticipoRecibido = anticipoRecibido; }
    public String getCondicionesAdicionales() { return condicionesAdicionales; }
    public void setCondicionesAdicionales(String condicionesAdicionales) { this.condicionesAdicionales = condicionesAdicionales; }
}