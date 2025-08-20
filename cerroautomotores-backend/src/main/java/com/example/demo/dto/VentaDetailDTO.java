package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Si usas Lombok, puedes añadir @Data y quitar los getters/setters.
public class VentaDetailDTO {

    private Long idVenta;
    private LocalDate fechaOperacion;
    private ClienteDTO cliente;
    private EmpleadoDTO empleadoVendedor;
    private String clasificacionVehiculoVendido;
    private String descripcionVehiculoManual;
    private BigDecimal precioVehiculoPactado;
    private String metodoPagoPrincipal;
    private BigDecimal montoTotalCobrado;
    private String tipoFinanciacion;
    private String estadoOperacion;
    private List<GastoDTO> gastosOperativosVenta;
    
    // --- CAMPOS DE RENTABILIDAD Y LOGÍSTICA ---
    private BigDecimal costoVehiculoParaEmpresa;
    private BigDecimal comisionEmpleadoMonto;
    private BigDecimal comisionEmpleadoPorcentaje;
    private BigDecimal gananciaNetaEmpresa;
    private String observacionesGenerales;
    private LocalDate fechaEntregaEstimada;
    private LocalDate fechaEntregaEfectiva;

    // --- Detalles de Financiación ---
    private DetalleFinanciacionPropiaDTO detalleFinanciacionPropia;
    private DetalleFinanciacionPrendariaDTO detalleFinanciacionPrendaria;
    private DetalleInversionDTO detalleInversion;
    private PagoPrincipalCreditoDTO pagoPrincipalCredito;   // 🆕
    private List<DetallePagoDTO> detallesPago;              // 🆕

    public PagoPrincipalCreditoDTO getPagoPrincipalCredito() { return pagoPrincipalCredito; }
    public void setPagoPrincipalCredito(PagoPrincipalCreditoDTO pagoPrincipalCredito) { this.pagoPrincipalCredito = pagoPrincipalCredito; }

    public List<DetallePagoDTO> getDetallesPago() { return detallesPago; }
    public void setDetallesPago(List<DetallePagoDTO> detallesPago) { this.detallesPago = detallesPago; }

    // --- Constructor Vacío ---
    public VentaDetailDTO() {}

    // --- Getters y Setters ---
    // (Asegúrate de tenerlos para TODOS los campos. Eclipse: Source > Generate Getters and Setters...)
    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }
    public LocalDate getFechaOperacion() { return fechaOperacion; }
    public void setFechaOperacion(LocalDate fechaOperacion) { this.fechaOperacion = fechaOperacion; }
    public ClienteDTO getCliente() { return cliente; }
    public void setCliente(ClienteDTO cliente) { this.cliente = cliente; }
    public EmpleadoDTO getEmpleadoVendedor() { return empleadoVendedor; }
    public void setEmpleadoVendedor(EmpleadoDTO empleadoVendedor) { this.empleadoVendedor = empleadoVendedor; }
    public String getClasificacionVehiculoVendido() { return clasificacionVehiculoVendido; }
    public void setClasificacionVehiculoVendido(String clasificacionVehiculoVendido) { this.clasificacionVehiculoVendido = clasificacionVehiculoVendido; }
    public String getDescripcionVehiculoManual() { return descripcionVehiculoManual; }
    public void setDescripcionVehiculoManual(String descripcionVehiculoManual) { this.descripcionVehiculoManual = descripcionVehiculoManual; }
    public BigDecimal getPrecioVehiculoPactado() { return precioVehiculoPactado; }
    public void setPrecioVehiculoPactado(BigDecimal precioVehiculoPactado) { this.precioVehiculoPactado = precioVehiculoPactado; }
    public String getMetodoPagoPrincipal() { return metodoPagoPrincipal; }
    public void setMetodoPagoPrincipal(String metodoPagoPrincipal) { this.metodoPagoPrincipal = metodoPagoPrincipal; }
    public BigDecimal getMontoTotalCobrado() { return montoTotalCobrado; }
    public void setMontoTotalCobrado(BigDecimal montoTotalCobrado) { this.montoTotalCobrado = montoTotalCobrado; }
    public String getTipoFinanciacion() { return tipoFinanciacion; }
    public void setTipoFinanciacion(String tipoFinanciacion) { this.tipoFinanciacion = tipoFinanciacion; }
    public String getEstadoOperacion() { return estadoOperacion; }
    public void setEstadoOperacion(String estadoOperacion) { this.estadoOperacion = estadoOperacion; }
    public List<GastoDTO> getGastosOperativosVenta() { return gastosOperativosVenta; }
    public void setGastosOperativosVenta(List<GastoDTO> gastosOperativosVenta) { this.gastosOperativosVenta = gastosOperativosVenta; }
    public BigDecimal getCostoVehiculoParaEmpresa() { return costoVehiculoParaEmpresa; }
    public void setCostoVehiculoParaEmpresa(BigDecimal costoVehiculoParaEmpresa) { this.costoVehiculoParaEmpresa = costoVehiculoParaEmpresa; }
    public BigDecimal getComisionEmpleadoMonto() { return comisionEmpleadoMonto; }
    public void setComisionEmpleadoMonto(BigDecimal comisionEmpleadoMonto) { this.comisionEmpleadoMonto = comisionEmpleadoMonto; }
    public BigDecimal getComisionEmpleadoPorcentaje() { return comisionEmpleadoPorcentaje; }
    public void setComisionEmpleadoPorcentaje(BigDecimal comisionEmpleadoPorcentaje) { this.comisionEmpleadoPorcentaje = comisionEmpleadoPorcentaje; }
    public BigDecimal getGananciaNetaEmpresa() { return gananciaNetaEmpresa; }
    public void setGananciaNetaEmpresa(BigDecimal gananciaNetaEmpresa) { this.gananciaNetaEmpresa = gananciaNetaEmpresa; }
    public String getObservacionesGenerales() { return observacionesGenerales; }
    public void setObservacionesGenerales(String observacionesGenerales) { this.observacionesGenerales = observacionesGenerales; }
    public LocalDate getFechaEntregaEstimada() { return fechaEntregaEstimada; }
    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) { this.fechaEntregaEstimada = fechaEntregaEstimada; }
    public LocalDate getFechaEntregaEfectiva() { return fechaEntregaEfectiva; }
    public void setFechaEntregaEfectiva(LocalDate fechaEntregaEfectiva) { this.fechaEntregaEfectiva = fechaEntregaEfectiva; }
    public DetalleFinanciacionPropiaDTO getDetalleFinanciacionPropia() { return detalleFinanciacionPropia; }
    public void setDetalleFinanciacionPropia(DetalleFinanciacionPropiaDTO detalleFinanciacionPropia) { this.detalleFinanciacionPropia = detalleFinanciacionPropia; }
    public DetalleFinanciacionPrendariaDTO getDetalleFinanciacionPrendaria() { return detalleFinanciacionPrendaria; }
    public void setDetalleFinanciacionPrendaria(DetalleFinanciacionPrendariaDTO detalleFinanciacionPrendaria) { this.detalleFinanciacionPrendaria = detalleFinanciacionPrendaria; }
    public DetalleInversionDTO getDetalleInversion() { return detalleInversion; }
    public void setDetalleInversion(DetalleInversionDTO detalleInversion) { this.detalleInversion = detalleInversion; }
}
