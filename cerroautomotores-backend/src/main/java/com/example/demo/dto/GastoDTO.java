package com.example.demo.dto;

import java.math.BigDecimal;

public class GastoDTO {
    private String descripcion;
    private BigDecimal monto;

    // Getters y Setters
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
}