package com.example.demo.model;

import jakarta.persistence.*; // o javax.persistence.*
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "detalles_financiacion_prendaria")
public class DetalleFinanciacionPrendaria {

    @Id
    @Column(name = "id_venta_fk")
    private Long id; // Mismo ID que Venta.idVenta

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_venta_fk")
    @JsonBackReference
    private Venta venta;

    @Column(name = "entidad_financiera", nullable = false, length = 100)
    private String entidadFinanciera;

    @Column(name = "monto_credito_otorgado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoCreditoOtorgado;

    @Column(name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @Column(name = "valor_cuota_estimado", precision = 10, scale = 2)
    private BigDecimal valorCuotaEstimado;

    @Column(name = "numero_contrato_o_referencia", length = 100)
    private String numeroContratoORef;

    // --- Constructores ---
    public DetalleFinanciacionPrendaria() {
    }

    // --- Getters y Setters ---
    // (Generar con Eclipse o usar Lombok)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }
    public String getEntidadFinanciera() { return entidadFinanciera; }
    public void setEntidadFinanciera(String entidadFinanciera) { this.entidadFinanciera = entidadFinanciera; }
    public BigDecimal getMontoCreditoOtorgado() { return montoCreditoOtorgado; }
    public void setMontoCreditoOtorgado(BigDecimal montoCreditoOtorgado) { this.montoCreditoOtorgado = montoCreditoOtorgado; }
    public Integer getPlazoMeses() { return plazoMeses; }
    public void setPlazoMeses(Integer plazoMeses) { this.plazoMeses = plazoMeses; }
    public BigDecimal getValorCuotaEstimado() { return valorCuotaEstimado; }
    public void setValorCuotaEstimado(BigDecimal valorCuotaEstimado) { this.valorCuotaEstimado = valorCuotaEstimado; }
    public String getNumeroContratoORef() { return numeroContratoORef; }
    public void setNumeroContratoORef(String numeroContratoORef) { this.numeroContratoORef = numeroContratoORef; }
}