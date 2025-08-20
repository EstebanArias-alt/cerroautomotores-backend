package com.example.demo.dto;
import java.math.BigDecimal;
public class DetalleFinanciacionPropiaDTO {
	private BigDecimal montoFinanciado;
    private Integer cantidadCuotas;
    private BigDecimal valorCuotaAproximado;
    private BigDecimal tasaInteresNominalAnual;
    private BigDecimal costoFinancieroTotal;
    private BigDecimal anticipo;
    private String condiciones;
    private BigDecimal montoTotalConInteres;
    private BigDecimal valorCuota;
    private Integer cantidadCuotasPagadas;

   
	// Getters y Setters...
    public BigDecimal getMontoFinanciado() { return montoFinanciado; }
    public void setMontoFinanciado(BigDecimal montoFinanciado) { this.montoFinanciado = montoFinanciado; }
    public Integer getCantidadCuotas() { return cantidadCuotas; }
    public void setCantidadCuotas(Integer cantidadCuotas) { this.cantidadCuotas = cantidadCuotas; }
    public BigDecimal getValorCuotaAproximado() { return valorCuotaAproximado; }
    public void setValorCuotaAproximado(BigDecimal valorCuotaAproximado) { this.valorCuotaAproximado = valorCuotaAproximado; }
	public BigDecimal getTasaInteresNominalAnual() {
		return tasaInteresNominalAnual;
	}
	
	
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
	public void setTasaInteresNominalAnual(BigDecimal tasaInteresNominalAnual) {
		this.tasaInteresNominalAnual = tasaInteresNominalAnual;
	}
	public BigDecimal getCostoFinancieroTotal() {
		return costoFinancieroTotal;
	}
	public void setCostoFinancieroTotal(BigDecimal costoFinancieroTotal) {
		this.costoFinancieroTotal = costoFinancieroTotal;
	}
	public BigDecimal getAnticipo() {
		return anticipo;
	}
	public void setAnticipo(BigDecimal anticipo) {
		this.anticipo = anticipo;
	}
	public String getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}
    
}