package com.example.demo.dto;

//package com.example.demo.dto;

import java.math.BigDecimal;

public class PagoPrincipalCreditoDTO {
 private BigDecimal montoTotalCredito;
 private Integer cantidadCuotasCredito;
 private BigDecimal montoCuotaCredito;

 public BigDecimal getMontoTotalCredito() { return montoTotalCredito; }
 public void setMontoTotalCredito(BigDecimal montoTotalCredito) { this.montoTotalCredito = montoTotalCredito; }

 public Integer getCantidadCuotasCredito() { return cantidadCuotasCredito; }
 public void setCantidadCuotasCredito(Integer cantidadCuotasCredito) { this.cantidadCuotasCredito = cantidadCuotasCredito; }

 public BigDecimal getMontoCuotaCredito() { return montoCuotaCredito; }
 public void setMontoCuotaCredito(BigDecimal montoCuotaCredito) { this.montoCuotaCredito = montoCuotaCredito; }
}

