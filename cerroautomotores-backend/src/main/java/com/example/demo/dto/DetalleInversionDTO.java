package com.example.demo.dto;
import java.math.BigDecimal;
public class DetalleInversionDTO {
    private String codigo;
    private Integer anio;
    private BigDecimal precio;
    private Integer cuotas;
    // Getters y Setters...
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Integer getCuotas() { return cuotas; }
    public void setCuotas(Integer cuotas) { this.cuotas = cuotas; }
}