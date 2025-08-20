package com.example.demo.controller;

import com.example.demo.dto.VentaDetailDTO;
import com.example.demo.dto.VentaListDTO;
import com.example.demo.model.Venta;
import com.example.demo.service.VentaService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:4200")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody Venta venta) {
        try {
            Venta ventaGuardada = ventaService.crearVenta(venta);
            VentaDetailDTO ventaDTO = ventaService.convertirVentaAVentaDetailDTO(ventaGuardada);
            return new ResponseEntity<>(ventaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al crear la venta: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<VentaListDTO>> obtenerTodasLasVentas(
            @RequestParam(name = "filtro", required = false) String filtro) {
        List<VentaListDTO> ventasDTO = ventaService.obtenerTodasLasVentas(filtro);
        return ResponseEntity.ok(ventasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable Long id) {
        Optional<VentaDetailDTO> ventaDTOOptional = ventaService.obtenerVentaPorId(id);
        
        return ventaDTOOptional
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVenta(@PathVariable Long id, @RequestBody Venta ventaDetalles) {
        try {
            Venta ventaActualizada = ventaService.actualizarVenta(id, ventaDetalles);
            VentaDetailDTO ventaDTO = ventaService.convertirVentaAVentaDetailDTO(ventaActualizada);
            return ResponseEntity.ok(ventaDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        try {
            ventaService.eliminarVenta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{id}/cuotas-pagadas")
    public ResponseEntity<Void> actualizarCuotasPagadas(@PathVariable Long id, @RequestBody Integer cantidadPagadas) {
        ventaService.actualizarCantidadCuotasPagadas(id, cantidadPagadas);
        return ResponseEntity.ok().build();
    }
}
