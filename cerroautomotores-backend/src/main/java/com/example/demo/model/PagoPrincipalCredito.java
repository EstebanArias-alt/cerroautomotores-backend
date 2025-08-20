package com.example.demo.model;

//package com.example.demo.model;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class PagoPrincipalCredito {

 private BigDecimal montoTotalCredito;
 private Integer cantidadCuotasCredito;
 private BigDecimal montoCuotaCredito;

 // Getters & Setters
 public BigDecimal getMontoTotalCredito() { return montoTotalCredito; }
 public void setMontoTotalCredito(BigDecimal montoTotalCredito) { this.montoTotalCredito = montoTotalCredito; }

 public Integer getCantidadCuotasCredito() { return cantidadCuotasCredito; }
 public void setCantidadCuotasCredito(Integer cantidadCuotasCredito) { this.cantidadCuotasCredito = cantidadCuotasCredito; }

 public BigDecimal getMontoCuotaCredito() { return montoCuotaCredito; }
 public void setMontoCuotaCredito(BigDecimal montoCuotaCredito) { this.montoCuotaCredito = montoCuotaCredito; }
}
