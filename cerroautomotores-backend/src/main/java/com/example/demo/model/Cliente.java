package com.example.demo.model; // Asegúrate que el paquete sea el correcto

import jakarta.persistence.*; // Para Spring Boot 3.x y Jakarta EE 9+
// Si usas Spring Boot 2.x y Java EE, sería: import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre_razon_social", nullable = false, length = 255)
    private String nombreRazonSocial;

    @Column(name = "dni_cuit", unique = true, length = 20)
    private String dniCuit;

    @Column(length = 50)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(length = 255)
    private String direccion;

    @Column(length = 100)
    private String localidad;

    @Column(length = 100)
    private String provincia;

    @Column(name = "condicion_iva", length = 50)
    private String condicionIva;

    @Column(name = "fecha_alta", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaAlta;

    // Constructores
    public Cliente() {
    }

    // Getters y Setters
    // (Genera con Eclipse: Clic derecho > Source > Generate Getters and Setters... > Select All > Generate)
    // O si añadiste Lombok a tu pom.xml, puedes anotar la clase con @Data o @Getter @Setter @NoArgsConstructor @AllArgsConstructor

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
    public String getNombreRazonSocial() { return nombreRazonSocial; }
    public void setNombreRazonSocial(String nombreRazonSocial) { this.nombreRazonSocial = nombreRazonSocial; }
    public String getDniCuit() { return dniCuit; }
    public void setDniCuit(String dniCuit) { this.dniCuit = dniCuit; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }
    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
    public String getCondicionIva() { return condicionIva; }
    public void setCondicionIva(String condicionIva) { this.condicionIva = condicionIva; }
    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; }

    @PrePersist
    protected void onCreate() {
        if (this.fechaAlta == null) {
            this.fechaAlta = LocalDateTime.now();
        }
    }
}