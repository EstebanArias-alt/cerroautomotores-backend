package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// Si usas Lombok, puedes añadir @Data aquí y borrar los getters/setters.
public class VentaListDTO {

    private Long idVenta;
    private LocalDate fechaOperacion;
    private String nombreCliente;
    private String nombreVendedor;
    private String clasificacionVehiculo;
    private String descripcionVehiculoManual; // Para el "Modelo"
    private BigDecimal precioPactado;
    private BigDecimal totalGastos; // Suma de todos los gastos
    private String tipoFinanciacion;
    private BigDecimal comisionEmpleadoMonto;
    private BigDecimal gananciaNetaEmpresa;
    private String estadoOperacion;
    private String direccionCliente;
    public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public String getLocalidadCliente() {
		return localidadCliente;
	}
	public void setLocalidadCliente(String localidadCliente) {
		this.localidadCliente = localidadCliente;
	}
	public String getCondicionIvaCliente() {
		return condicionIvaCliente;
	}
	public void setCondicionIvaCliente(String condicionIvaCliente) {
		this.condicionIvaCliente = condicionIvaCliente;
	}
	private String localidadCliente;
    private String condicionIvaCliente;
    // Constructor, Getters y Setters...
    public VentaListDTO() {}

    // No olvides generar los Getters y Setters para los nuevos campos
    // (Clic derecho > Source > Generate Getters and Setters...)
    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }
    public LocalDate getFechaOperacion() { return fechaOperacion; }
    public void setFechaOperacion(LocalDate fechaOperacion) { this.fechaOperacion = fechaOperacion; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getNombreVendedor() { return nombreVendedor; }
    public void setNombreVendedor(String nombreVendedor) { this.nombreVendedor = nombreVendedor; }
    public String getClasificacionVehiculo() { return clasificacionVehiculo; }
    public void setClasificacionVehiculo(String clasificacionVehiculo) { this.clasificacionVehiculo = clasificacionVehiculo; }
    public String getDescripcionVehiculoManual() { return descripcionVehiculoManual; }
    public void setDescripcionVehiculoManual(String descripcionVehiculoManual) { this.descripcionVehiculoManual = descripcionVehiculoManual; }
    public BigDecimal getPrecioPactado() { return precioPactado; }
    public void setPrecioPactado(BigDecimal precioPactado) { this.precioPactado = precioPactado; }
    public BigDecimal getTotalGastos() { return totalGastos; }
    public void setTotalGastos(BigDecimal totalGastos) { this.totalGastos = totalGastos; }
    public String getTipoFinanciacion() { return tipoFinanciacion; }
    public void setTipoFinanciacion(String tipoFinanciacion) { this.tipoFinanciacion = tipoFinanciacion; }
    public BigDecimal getComisionEmpleadoMonto() { return comisionEmpleadoMonto; }
    public void setComisionEmpleadoMonto(BigDecimal comisionEmpleadoMonto) { this.comisionEmpleadoMonto = comisionEmpleadoMonto; }
    public BigDecimal getGananciaNetaEmpresa() { return gananciaNetaEmpresa; }
    public void setGananciaNetaEmpresa(BigDecimal gananciaNetaEmpresa) { this.gananciaNetaEmpresa = gananciaNetaEmpresa; }
    public String getEstadoOperacion() { return estadoOperacion; }
    public void setEstadoOperacion(String estadoOperacion) { this.estadoOperacion = estadoOperacion; }
}