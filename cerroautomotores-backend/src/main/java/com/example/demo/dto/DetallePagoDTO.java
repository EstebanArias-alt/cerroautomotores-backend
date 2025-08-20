package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.math.BigDecimal;

public class DetallePagoDTO {

    // Acepta "metodo" (backend legacy) y "tipo" (front nuevo)
    @JsonAlias({"metodo", "tipo"})
    private String metodo;

    private BigDecimal monto; // usado cuando NO es crédito

    // Acepta "referencia" (legacy) y "observacion" (front nuevo)
    @JsonAlias({"referencia", "observacion"})
    private String referencia;

    // 🔹 Campos opcionales para crédito
    private BigDecimal montoTotalCredito;
    private Integer cuotas;
    private BigDecimal montoCuota;

    // Getters / Setters
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
    public BigDecimal getMontoTotalCredito() { return montoTotalCredito; }
    public void setMontoTotalCredito(BigDecimal montoTotalCredito) { this.montoTotalCredito = montoTotalCredito; }
    public Integer getCuotas() { return cuotas; }
    public void setCuotas(Integer cuotas) { this.cuotas = cuotas; }
    public BigDecimal getMontoCuota() { return montoCuota; }
    public void setMontoCuota(BigDecimal montoCuota) { this.montoCuota = montoCuota; }
}
