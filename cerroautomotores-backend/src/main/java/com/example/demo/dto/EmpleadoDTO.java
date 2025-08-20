package com.example.demo.dto;

public class EmpleadoDTO {

    private Long idEmpleado;
    private String nombreCompleto;

    // Constructor vacío
    public EmpleadoDTO() {}

    // Getters y Setters
    // (Genera con Eclipse o usa Lombok)

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
}