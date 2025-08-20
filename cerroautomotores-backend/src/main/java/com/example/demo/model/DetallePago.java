package com.example.demo.model;

//package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class DetallePago {

 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "venta_id")
 private Venta venta;

 private String tipo;                    // "Efectivo", "Transferencia", "Crédito Banco X", etc.
 private BigDecimal monto;               // usado si NO es crédito

 // Campos cuando ES crédito
 private BigDecimal montoTotalCredito;
 private Integer cuotas;
 private BigDecimal montoCuota;

 @Column(length = 500)
 private String observacion;

 // Getters/Setters
 public Long getId() { return id; }

 public Venta getVenta() { return venta; }
 public void setVenta(Venta venta) { this.venta = venta; }

 public String getTipo() { return tipo; }
 public void setTipo(String tipo) { this.tipo = tipo; }

 public BigDecimal getMonto() { return monto; }
 public void setMonto(BigDecimal monto) { this.monto = monto; }

 public BigDecimal getMontoTotalCredito() { return montoTotalCredito; }
 public void setMontoTotalCredito(BigDecimal montoTotalCredito) { this.montoTotalCredito = montoTotalCredito; }

 public Integer getCuotas() { return cuotas; }
 public void setCuotas(Integer cuotas) { this.cuotas = cuotas; }

 public BigDecimal getMontoCuota() { return montoCuota; }
 public void setMontoCuota(BigDecimal montoCuota) { this.montoCuota = montoCuota; }

 public String getObservacion() { return observacion; }
 public void setObservacion(String observacion) { this.observacion = observacion; }
}
