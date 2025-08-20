package com.example.demo.model; // Asegúrate que el paquete sea com.example.demo.model

import jakarta.persistence.*; // o javax.persistence.* para Spring Boot 2.x
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "nombre_completo", nullable = false, length = 255)
    private String nombreCompleto;

    @Column(length = 100)
    private String puesto; // Ej: "Vendedor", "Administrativo"

    @Column(name = "fecha_ingreso_empleado")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaIngresoEmpleado;

    @Column(nullable = false)
    private boolean activo = true; // Valor por defecto

    // --- Constructores ---
    public Empleado() {
        // Constructor vacío requerido por JPA
    }

    // --- Getters y Setters ---
    // (Genera con Eclipse: Clic derecho > Source > Generate Getters and Setters... > Select All > Generate)
    // O si usas Lombok, anota la clase con @Data o @Getter @Setter, etc.

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public LocalDate getFechaIngresoEmpleado() {
        return fechaIngresoEmpleado;
    }

    public void setFechaIngresoEmpleado(LocalDate fechaIngresoEmpleado) {
        this.fechaIngresoEmpleado = fechaIngresoEmpleado;
    }

    public boolean isActivo() { // Para boolean, el getter suele ser isActivo()
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}