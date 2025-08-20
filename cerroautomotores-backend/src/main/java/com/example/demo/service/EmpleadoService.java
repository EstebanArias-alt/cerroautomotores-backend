package com.example.demo.service;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /**
     * Obtiene todos los empleados de la base de datos.
     * @return una lista de todos los empleados.
     */
    @Transactional(readOnly = true)
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    /**
     * Obtiene un empleado por su ID.
     * @param id El ID del empleado.
     * @return un Optional que contiene al empleado si se encuentra.
     */
    @Transactional(readOnly = true)
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    /**
     * Guarda un nuevo empleado o actualiza uno existente.
     * @param empleado El objeto Empleado a guardar.
     * @return el empleado guardado.
     */
    @Transactional
    public Empleado guardarEmpleado(Empleado empleado) {
        // Aquí se podría añadir lógica de validación en el futuro.
        return empleadoRepository.save(empleado);
    }

    /**
     * Elimina un empleado por su ID.
     * @param id El ID del empleado a eliminar.
     */
    @Transactional
    public void eliminarEmpleado(Long id) {
        // Se podría añadir una verificación para no eliminar empleados con ventas asociadas.
        empleadoRepository.deleteById(id);
    }
}
