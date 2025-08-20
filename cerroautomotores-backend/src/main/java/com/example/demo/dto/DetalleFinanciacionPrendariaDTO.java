package com.example.demo.dto;
import java.math.BigDecimal;
public class DetalleFinanciacionPrendariaDTO {
	private String entidadFinanciera;
    private BigDecimal montoCreditoOtorgado;
    private Integer plazoMeses;
    private BigDecimal valorCuotaEstimado;
    private String numeroContratoORef;
    // Getters y Setters...
    public String getEntidadFinanciera() { return entidadFinanciera; }
    public void setEntidadFinanciera(String entidadFinanciera) { this.entidadFinanciera = entidadFinanciera; }
    public BigDecimal getMontoCreditoOtorgado() { return montoCreditoOtorgado; }
    public void setMontoCreditoOtorgado(BigDecimal montoCreditoOtorgado) { this.montoCreditoOtorgado = montoCreditoOtorgado; }
    public Integer getPlazoMeses() { return plazoMeses; }
    public void setPlazoMeses(Integer plazoMeses) { this.plazoMeses = plazoMeses; }
	public BigDecimal getValorCuotaEstimado() {
		return valorCuotaEstimado;
	}
	public void setValorCuotaEstimado(BigDecimal valorCuotaEstimado) {
		this.valorCuotaEstimado = valorCuotaEstimado;
	}
	public String getNumeroContratoORef() {
		return numeroContratoORef;
	}
	public void setNumeroContratoORef(String numeroContratoORef) {
		this.numeroContratoORef = numeroContratoORef;
	}
    
}