	package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList; 

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// o javax.persistence.*
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name = "ventas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_operacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fechaOperacion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_cliente_fk", nullable = false)
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado_fk", nullable = false)
    private Empleado empleadoVendedor;

    @Column(name = "clasificacion_vehiculo_vendido", nullable = false, length = 10)
    private String clasificacionVehiculoVendido;

    @Lob // Para campos de texto más largos, se mapea a TEXT o similar
    @Column(name = "descripcion_vehiculo_manual")
    private String descripcionVehiculoManual;

    @Column(name = "precio_vehiculo_pactado", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioVehiculoPactado;

    @Column(name = "metodo_pago_principal", nullable = false, length = 50)
    private String metodoPagoPrincipal;

    @Column(name = "monto_total_cobrado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoTotalCobrado;

    @Column(name = "tipo_financiacion", nullable = false, length = 50)
    private String tipoFinanciacion;

    @Column(name = "costo_vehiculo_para_empresa", precision = 12, scale = 2)
    private BigDecimal costoVehiculoParaEmpresa;

    @Column(name = "comision_empleado_monto", precision = 10, scale = 2)
    private BigDecimal comisionEmpleadoMonto;

    @Column(name = "comision_empleado_porcentaje", precision = 5, scale = 2)
    private BigDecimal comisionEmpleadoPorcentaje;

    @Column(name = "ganancia_bruta_empresa", precision = 12, scale = 2)
    private BigDecimal gananciaBrutaEmpresa; // Podría ser calculado

    @Column(name = "ganancia_neta_empresa", precision = 12, scale = 2)
    private BigDecimal gananciaNetaEmpresa; // Podría ser calculado

    @Column(name = "fecha_entrega_estimada")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaEntregaEstimada;

    @Column(name = "fecha_entrega_efectiva")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaEntregaEfectiva;

    @Column(name = "estado_operacion", nullable = false, length = 50)
    private String estadoOperacion;

    @Lob
    @Column(name = "observaciones_generales")
    private String observacionesGenerales;

    @Column(name = "fecha_registro_venta", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaRegistroVenta;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference 
    private List<GastoOperativo> gastosOperativosVenta = new ArrayList<>();
    
    @OneToOne(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference
    private DetalleFinanciacionPropia detalleFinanciacionPropia;
    
    @OneToOne(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference
    private DetalleFinanciacionPrendaria detalleFinanciacionPrendaria;
    
    @OneToOne(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, optional = true)
    @JsonManagedReference(value="venta-inversion") // Nombre único para la referencia
    private DetalleInversion detalleInversion;
    
    @Embedded
    private PagoPrincipalCredito pagoPrincipalCredito;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePago> detallesPago = new ArrayList<>();
    
    
    public DetalleFinanciacionPropia getDetalleFinanciacionPropia() { return detalleFinanciacionPropia; }
    public void setDetalleFinanciacionPropia(DetalleFinanciacionPropia detalleFinanciacionPropia) {
        this.detalleFinanciacionPropia = detalleFinanciacionPropia;
        // Si se establece el detalle, también establecer la referencia inversa
        if (detalleFinanciacionPropia != null) {
            detalleFinanciacionPropia.setVenta(this);
        }
    }
    
    public DetalleFinanciacionPrendaria getDetalleFinanciacionPrendaria() { return detalleFinanciacionPrendaria; }
    public void setDetalleFinanciacionPrendaria(DetalleFinanciacionPrendaria detalleFinanciacionPrendaria) {
        this.detalleFinanciacionPrendaria = detalleFinanciacionPrendaria;
        if (detalleFinanciacionPrendaria != null) {
            detalleFinanciacionPrendaria.setVenta(this);
        }
    }

    // --- Constructores ---
    public Venta() {
    }

    // --- Getters y Setters ---
    // (No olvides generar getter y setter para gastosOperativosVenta)

    public List<GastoOperativo> getGastosOperativosVenta() { return gastosOperativosVenta; }
    public void setGastosOperativosVenta(List<GastoOperativo> gastosOperativosVenta) { this.gastosOperativosVenta = gastosOperativosVenta; }

    // (Helper method para añadir gastos de forma conveniente - opcional)

    public void addGasto(GastoOperativo gasto) {
        if (gasto != null) {
            if (this.gastosOperativosVenta == null) {
                this.gastosOperativosVenta = new ArrayList<>();
            }
            this.gastosOperativosVenta.add(gasto);
            gasto.setVenta(this); // <-- ESTA LÍNEA ES LA CLAVE
        }
    }
   

    // --- Getters y Setters ---
    // (Genera con Eclipse o usa Lombok)

    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }
    public LocalDate getFechaOperacion() { return fechaOperacion; }
    public void setFechaOperacion(LocalDate fechaOperacion) { this.fechaOperacion = fechaOperacion; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Empleado getEmpleadoVendedor() { return empleadoVendedor; }
    public void setEmpleadoVendedor(Empleado empleadoVendedor) { this.empleadoVendedor = empleadoVendedor; }
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
    public BigDecimal getCostoVehiculoParaEmpresa() { return costoVehiculoParaEmpresa; }
    public void setCostoVehiculoParaEmpresa(BigDecimal costoVehiculoParaEmpresa) { this.costoVehiculoParaEmpresa = costoVehiculoParaEmpresa; }
    public BigDecimal getComisionEmpleadoMonto() { return comisionEmpleadoMonto; }
    public void setComisionEmpleadoMonto(BigDecimal comisionEmpleadoMonto) { this.comisionEmpleadoMonto = comisionEmpleadoMonto; }
    public BigDecimal getComisionEmpleadoPorcentaje() { return comisionEmpleadoPorcentaje; }
    public void setComisionEmpleadoPorcentaje(BigDecimal comisionEmpleadoPorcentaje) { this.comisionEmpleadoPorcentaje = comisionEmpleadoPorcentaje; }
    public BigDecimal getGananciaBrutaEmpresa() { return gananciaBrutaEmpresa; }
    public void setGananciaBrutaEmpresa(BigDecimal gananciaBrutaEmpresa) { this.gananciaBrutaEmpresa = gananciaBrutaEmpresa; }
    public BigDecimal getGananciaNetaEmpresa() { return gananciaNetaEmpresa; }
    public void setGananciaNetaEmpresa(BigDecimal gananciaNetaEmpresa) { this.gananciaNetaEmpresa = gananciaNetaEmpresa; }
    public LocalDate getFechaEntregaEstimada() { return fechaEntregaEstimada; }
    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) { this.fechaEntregaEstimada = fechaEntregaEstimada; }
    public LocalDate getFechaEntregaEfectiva() { return fechaEntregaEfectiva; }
    public void setFechaEntregaEfectiva(LocalDate fechaEntregaEfectiva) { this.fechaEntregaEfectiva = fechaEntregaEfectiva; }
    public String getEstadoOperacion() { return estadoOperacion; }
    public void setEstadoOperacion(String estadoOperacion) { this.estadoOperacion = estadoOperacion; }
    public String getObservacionesGenerales() { return observacionesGenerales; }
    public void setObservacionesGenerales(String observacionesGenerales) { this.observacionesGenerales = observacionesGenerales; }
    public LocalDateTime getFechaRegistroVenta() { return fechaRegistroVenta; }
    public void setFechaRegistroVenta(LocalDateTime fechaRegistroVenta) { this.fechaRegistroVenta = fechaRegistroVenta; }
    public DetalleInversion getDetalleInversion() {
        return detalleInversion;
    }
    
    public PagoPrincipalCredito getPagoPrincipalCredito() { return pagoPrincipalCredito; }
    public void setPagoPrincipalCredito(PagoPrincipalCredito pagoPrincipalCredito) { this.pagoPrincipalCredito = pagoPrincipalCredito; }

    public List<DetallePago> getDetallesPago() { return detallesPago; }
    public void setDetallesPago(List<DetallePago> detallesPago) { this.detallesPago = detallesPago; }

    public void setDetalleInversion(DetalleInversion detalleInversion) {
        this.detalleInversion = detalleInversion;
        // Asegura la relación bidireccional si el detalle no es nulo
        if (detalleInversion != null) {
            detalleInversion.setVenta(this);
        }
    }
    @PrePersist
    protected void onCreate() {
        if (this.fechaRegistroVenta == null) {
            this.fechaRegistroVenta = LocalDateTime.now();
        }
    }
}