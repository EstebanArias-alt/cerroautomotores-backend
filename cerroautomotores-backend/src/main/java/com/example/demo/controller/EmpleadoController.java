package com.example.demo.controller;

import com.example.demo.model.Empleado;
import com.example.demo.service.EmpleadoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {

    private final EmpleadoService empleadoService;


    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = empleadoService.obtenerTodos();
        return ResponseEntity.ok(empleados);
    }

    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.guardarEmpleado(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    // --- INICIO DEL NUEVO MÉTODO PARA ACTUALIZAR ---
    /**
     * Endpoint para ACTUALIZAR un Empleado existente.
     * Se activa con una petición HTTP PUT a /api/empleados/{id}
     * @param id El ID del empleado a actualizar.
     * @param empleadoDetalles El objeto Empleado con los nuevos datos.
     * @return El empleado actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) {
        // Buscamos el empleado existente por su ID
        return empleadoService.obtenerPorId(id)
                .map(empleadoExistente -> {
                    // Actualizamos los campos del empleado que ya está en la base de datos
                    empleadoExistente.setNombreCompleto(empleadoDetalles.getNombreCompleto());
                    empleadoExistente.setPuesto(empleadoDetalles.getPuesto());
                    empleadoExistente.setFechaIngresoEmpleado(empleadoDetalles.getFechaIngresoEmpleado());
                    empleadoExistente.setActivo(empleadoDetalles.isActivo());
                    
                    // Guardamos el empleado con los cambios aplicados
                    Empleado empleadoActualizado = empleadoService.guardarEmpleado(empleadoExistente);
                    return ResponseEntity.ok(empleadoActualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no se encuentra, devuelve 404
    }
    // --- FIN DEL NUEVO MÉTODO ---
}
