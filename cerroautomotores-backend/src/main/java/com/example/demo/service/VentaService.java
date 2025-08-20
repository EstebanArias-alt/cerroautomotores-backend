package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;

    public VentaService(VentaRepository ventaRepository,
                        ClienteRepository clienteRepository,
                        EmpleadoRepository empleadoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Transactional
    public Venta crearVenta(Venta venta) {
        // --- INICIO DEL LOGGING DE DEPURACIÓN ---
        System.out.println("=================================================");
        System.out.println(">>> [DEBUG] Iniciando proceso de crearVenta...");
        System.out.println(">>> [DEBUG] Tipo de Financiación recibido: " + venta.getTipoFinanciacion());

        if (venta.getDetalleFinanciacionPropia() != null) {
            System.out.println(">>> [DEBUG] Recibido DetalleFinanciacionPropia: Monto=" + venta.getDetalleFinanciacionPropia().getMontoFinanciado());
        } else {
            System.out.println(">>> [DEBUG] Recibido DetalleFinanciacionPropia: NULL");
        }

        if (venta.getDetalleFinanciacionPrendaria() != null) {
            System.out.println(">>> [DEBUG] Recibido DetalleFinanciacionPrendaria: Entidad=" + venta.getDetalleFinanciacionPrendaria().getEntidadFinanciera());
        } else {
            System.out.println(">>> [DEBUG] Recibido DetalleFinanciacionPrendaria: NULL");
        }
        
        if (venta.getDetalleInversion() != null) {
            System.out.println(">>> [DEBUG] Recibido DetalleInversion: Código=" + venta.getDetalleInversion().getCodigo());
        } else {
            System.out.println(">>> [DEBUG] Recibido DetalleInversion: NULL");
        }
        System.out.println("=================================================");
        // --- FIN DEL LOGGING DE DEPURACIÓN ---

        // Lógica para asociar Cliente y Empleado
        if (venta.getCliente() != null && venta.getCliente().getIdCliente() == null && venta.getCliente().getDniCuit() != null) {
            clienteRepository.findByDniCuit(venta.getCliente().getDniCuit()).ifPresent(venta::setCliente);
        }
        if (venta.getEmpleadoVendedor() != null && venta.getEmpleadoVendedor().getIdEmpleado() != null) {
            Empleado empleado = empleadoRepository.findById(venta.getEmpleadoVendedor().getIdEmpleado())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + venta.getEmpleadoVendedor().getIdEmpleado()));
            venta.setEmpleadoVendedor(empleado);
        } else {
             throw new RuntimeException("ID de Empleado Vendedor es requerido.");
        }

        // Establecer relaciones bidireccionales
        if (venta.getGastosOperativosVenta() != null) {
            venta.getGastosOperativosVenta().forEach(gasto -> gasto.setVenta(venta));
        }
        if (venta.getDetalleFinanciacionPropia() != null) {
            venta.getDetalleFinanciacionPropia().setVenta(venta);
        }
        if (venta.getDetalleFinanciacionPrendaria() != null) {
            venta.getDetalleFinanciacionPrendaria().setVenta(venta);
        }
        if (venta.getDetalleInversion() != null) {
            venta.getDetalleInversion().setVenta(venta);
        }
        
        if (venta.getGastosOperativosVenta() != null) {
            venta.getGastosOperativosVenta().forEach(gasto -> gasto.setVenta(venta));
        }
        if (venta.getDetalleFinanciacionPropia() != null) {
            venta.getDetalleFinanciacionPropia().setVenta(venta);
        }
        if (venta.getDetalleFinanciacionPrendaria() != null) {
            venta.getDetalleFinanciacionPrendaria().setVenta(venta);
        }
        if (venta.getDetalleInversion() != null) {
            venta.getDetalleInversion().setVenta(venta);
        }

        // 🆕 linkear detalles de pago al padre
        if (venta.getDetallesPago() != null) {
            venta.getDetallesPago().forEach(dp -> dp.setVenta(venta));
        }

        // 🆕 (opcional) log rápido de los nuevos datos
        if (venta.getPagoPrincipalCredito() != null) {
            System.out.println(">>> [DEBUG] Principal Crédito: total="
                + venta.getPagoPrincipalCredito().getMontoTotalCredito()
                + " cuotas=" + venta.getPagoPrincipalCredito().getCantidadCuotasCredito()
                + " montoCuota=" + venta.getPagoPrincipalCredito().getMontoCuotaCredito());
        }
        System.out.println(">>> [DEBUG] DetallesPago cantidad: " 
            + (venta.getDetallesPago() == null ? 0 : venta.getDetallesPago().size()));

        return ventaRepository.save(venta);
    }
    
    @Transactional(readOnly = true)
    public List<VentaListDTO> obtenerTodasLasVentas(String searchTerm) {
        List<Venta> ventas;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            ventas = ventaRepository.searchByCliente(searchTerm);
        } else {
            ventas = ventaRepository.findAll();
        }
        return ventas.stream()
                .map(this::convertirVentaAVentaListDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<VentaDetailDTO> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id)
                .map(this::convertirVentaAVentaDetailDTO);
    }
    
    @Transactional
    public Venta actualizarVenta(Long id, Venta ventaConNuevosDatos) {
        // 1. Busca la Venta existente
        Venta ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));

        // 2. Actualiza los campos simples
        ventaExistente.setFechaOperacion(ventaConNuevosDatos.getFechaOperacion());
        ventaExistente.setClasificacionVehiculoVendido(ventaConNuevosDatos.getClasificacionVehiculoVendido());
        ventaExistente.setDescripcionVehiculoManual(ventaConNuevosDatos.getDescripcionVehiculoManual());
        ventaExistente.setPrecioVehiculoPactado(ventaConNuevosDatos.getPrecioVehiculoPactado());
        ventaExistente.setMetodoPagoPrincipal(ventaConNuevosDatos.getMetodoPagoPrincipal());
        ventaExistente.setMontoTotalCobrado(ventaConNuevosDatos.getMontoTotalCobrado());
        ventaExistente.setEstadoOperacion(ventaConNuevosDatos.getEstadoOperacion());
        ventaExistente.setTipoFinanciacion(ventaConNuevosDatos.getTipoFinanciacion());
        ventaExistente.setCostoVehiculoParaEmpresa(ventaConNuevosDatos.getCostoVehiculoParaEmpresa());
        ventaExistente.setComisionEmpleadoMonto(ventaConNuevosDatos.getComisionEmpleadoMonto());
        ventaExistente.setComisionEmpleadoPorcentaje(ventaConNuevosDatos.getComisionEmpleadoPorcentaje());
        ventaExistente.setObservacionesGenerales(ventaConNuevosDatos.getObservacionesGenerales());
        ventaExistente.setFechaEntregaEstimada(ventaConNuevosDatos.getFechaEntregaEstimada());
        ventaExistente.setFechaEntregaEfectiva(ventaConNuevosDatos.getFechaEntregaEfectiva());
        
        // 3. Actualiza el Cliente
        Cliente clienteExistente = ventaExistente.getCliente();
        Cliente clienteConNuevosDatos = ventaConNuevosDatos.getCliente();
        if (clienteExistente != null && clienteConNuevosDatos != null) {
            clienteExistente.setNombreRazonSocial(clienteConNuevosDatos.getNombreRazonSocial());
            clienteExistente.setDniCuit(clienteConNuevosDatos.getDniCuit());
            clienteExistente.setTelefono(clienteConNuevosDatos.getTelefono());
            clienteExistente.setEmail(clienteConNuevosDatos.getEmail());
        }

        // 4. Actualiza el Empleado
        if (ventaConNuevosDatos.getEmpleadoVendedor() != null && ventaConNuevosDatos.getEmpleadoVendedor().getIdEmpleado() != null) {
            Empleado empleado = empleadoRepository.findById(ventaConNuevosDatos.getEmpleadoVendedor().getIdEmpleado())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            ventaExistente.setEmpleadoVendedor(empleado);
        }
        
        // 5. Actualiza los Gastos Operativos
        ventaExistente.getGastosOperativosVenta().clear();
        if (ventaConNuevosDatos.getGastosOperativosVenta() != null) {
            ventaConNuevosDatos.getGastosOperativosVenta().forEach(gastoNuevo -> {
                gastoNuevo.setVenta(ventaExistente);
                ventaExistente.getGastosOperativosVenta().add(gastoNuevo);
            });
        }

        // 6. Actualiza los detalles de financiación
        ventaExistente.setDetalleFinanciacionPropia(null);
        ventaExistente.setDetalleFinanciacionPrendaria(null);
        ventaExistente.setDetalleInversion(null);

        if ("Propia".equals(ventaConNuevosDatos.getTipoFinanciacion())) {
            if (ventaExistente.getDetalleFinanciacionPropia() != null) {
                DetalleFinanciacionPropia existente = ventaExistente.getDetalleFinanciacionPropia();
                DetalleFinanciacionPropia nuevo = ventaConNuevosDatos.getDetalleFinanciacionPropia();
                if (nuevo != null) {
                    existente.setMontoFinanciado(nuevo.getMontoFinanciado());
                    existente.setAnticipoRecibido(nuevo.getAnticipoRecibido());
                    existente.setCantidadCuotas(nuevo.getCantidadCuotas());
                    existente.setValorCuotaAproximado(nuevo.getValorCuotaAproximado());
                    existente.setTasaInteresNominalAnual(nuevo.getTasaInteresNominalAnual());
                    existente.setCostoFinancieroTotal(nuevo.getCostoFinancieroTotal());
                    existente.setCondicionesAdicionales(nuevo.getCondicionesAdicionales());
                    existente.setMontoTotalConInteres(nuevo.getMontoTotalConInteres());
                    existente.setValorCuota(nuevo.getValorCuota());
                }
            } else {
                DetalleFinanciacionPropia nuevo = ventaConNuevosDatos.getDetalleFinanciacionPropia();
                if (nuevo != null) {
                    nuevo.setVenta(ventaExistente);
                    ventaExistente.setDetalleFinanciacionPropia(nuevo);
                }
            }
        } else if ("Prendaria".equals(ventaConNuevosDatos.getTipoFinanciacion())) {
            if (ventaExistente.getDetalleFinanciacionPrendaria() != null) {
                DetalleFinanciacionPrendaria existente = ventaExistente.getDetalleFinanciacionPrendaria();
                DetalleFinanciacionPrendaria nuevo = ventaConNuevosDatos.getDetalleFinanciacionPrendaria();
                if (nuevo != null) {
                    existente.setEntidadFinanciera(nuevo.getEntidadFinanciera());
                    existente.setMontoCreditoOtorgado(nuevo.getMontoCreditoOtorgado());
                    existente.setPlazoMeses(nuevo.getPlazoMeses());
                    existente.setValorCuotaEstimado(nuevo.getValorCuotaEstimado());
                    existente.setNumeroContratoORef(nuevo.getNumeroContratoORef());
                }
            } else {
                DetalleFinanciacionPrendaria nuevo = ventaConNuevosDatos.getDetalleFinanciacionPrendaria();
                if (nuevo != null) {
                    nuevo.setVenta(ventaExistente);
                    ventaExistente.setDetalleFinanciacionPrendaria(nuevo);
                }
            }
        } else if ("Sistema de Inversion".equals(ventaConNuevosDatos.getTipoFinanciacion())) {
            if (ventaExistente.getDetalleInversion() != null) {
                DetalleInversion existente = ventaExistente.getDetalleInversion();
                DetalleInversion nuevo = ventaConNuevosDatos.getDetalleInversion();
                if (nuevo != null) {
                    existente.setCodigo(nuevo.getCodigo());
                    existente.setAnio(nuevo.getAnio());
                    existente.setPrecio(nuevo.getPrecio());
                    existente.setCuotas(nuevo.getCuotas());
                }
            } else {
                DetalleInversion nuevo = ventaConNuevosDatos.getDetalleInversion();
                if (nuevo != null) {
                    nuevo.setVenta(ventaExistente);
                    ventaExistente.setDetalleInversion(nuevo);
                }
            }
        }
        
     // 🆕 Principal: crédito embebido (simplemente reemplazar objeto completo)
        ventaExistente.setPagoPrincipalCredito(ventaConNuevosDatos.getPagoPrincipalCredito());

        // 🆕 Detalles de pago: limpiar y reemplazar (respeta orphanRemoval)
        ventaExistente.getDetallesPago().clear();
        if (ventaConNuevosDatos.getDetallesPago() != null) {
            for (DetallePago dp : ventaConNuevosDatos.getDetallesPago()) {
                dp.setVenta(ventaExistente);
                ventaExistente.getDetallesPago().add(dp);
            }
        }

        // 7. Guardar la entidad actualizada
        return ventaRepository.save(ventaExistente);
    }

    @Transactional
    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }

    // --- MÉTODOS DE CONVERSIÓN A DTO ---

    public VentaDetailDTO convertirVentaAVentaDetailDTO(Venta venta) {
        VentaDetailDTO dto = new VentaDetailDTO();
        
        // Mapeo de campos de Venta
        dto.setIdVenta(venta.getIdVenta());
        dto.setFechaOperacion(venta.getFechaOperacion());
        dto.setClasificacionVehiculoVendido(venta.getClasificacionVehiculoVendido());
        dto.setDescripcionVehiculoManual(venta.getDescripcionVehiculoManual());
        dto.setPrecioVehiculoPactado(venta.getPrecioVehiculoPactado());
        dto.setMetodoPagoPrincipal(venta.getMetodoPagoPrincipal());
        dto.setMontoTotalCobrado(venta.getMontoTotalCobrado());
        dto.setTipoFinanciacion(venta.getTipoFinanciacion());
        dto.setEstadoOperacion(venta.getEstadoOperacion());
        dto.setCostoVehiculoParaEmpresa(venta.getCostoVehiculoParaEmpresa());
        dto.setGananciaNetaEmpresa(venta.getGananciaNetaEmpresa());
        dto.setObservacionesGenerales(venta.getObservacionesGenerales());
        dto.setFechaEntregaEstimada(venta.getFechaEntregaEstimada());
        dto.setFechaEntregaEfectiva(venta.getFechaEntregaEfectiva());
        
        // Pasamos el porcentaje para mostrarlo en el HTML
        dto.setComisionEmpleadoPorcentaje(venta.getComisionEmpleadoPorcentaje());

        // --- LÓGICA DE CÁLCULO DE COMISIÓN ---
        BigDecimal comisionMonto = venta.getComisionEmpleadoMonto();
        BigDecimal comisionPorcentaje = venta.getComisionEmpleadoPorcentaje();
        
        // Si no hay un monto fijo pero sí un porcentaje, se calcula la comisión.
        if ((comisionMonto == null || comisionMonto.compareTo(BigDecimal.ZERO) == 0) && 
            (comisionPorcentaje != null && comisionPorcentaje.compareTo(BigDecimal.ZERO) > 0)) {
            
            BigDecimal precioPactado = venta.getPrecioVehiculoPactado();
            if (precioPactado != null) {
                // Calculamos: comision = (precio * porcentaje) / 100
                BigDecimal cien = new BigDecimal("100");
                comisionMonto = precioPactado.multiply(comisionPorcentaje).divide(cien, 2, RoundingMode.HALF_UP);
            }
        }
        
        // Asignamos el monto (sea el original o el calculado)
        dto.setComisionEmpleadoMonto(comisionMonto);
        
     // --- LÓGICA DE CÁLCULO DE GANANCIA NETA ---
        BigDecimal precioPactado = Optional.ofNullable(venta.getPrecioVehiculoPactado()).orElse(BigDecimal.ZERO);
        BigDecimal costoVehiculo = Optional.ofNullable(venta.getCostoVehiculoParaEmpresa()).orElse(BigDecimal.ZERO);
        BigDecimal comisionFinal = Optional.ofNullable(comisionMonto).orElse(BigDecimal.ZERO);

        BigDecimal totalGastos = BigDecimal.ZERO;
        if (venta.getGastosOperativosVenta() != null) {
            totalGastos = venta.getGastosOperativosVenta().stream()
                                .map(GastoOperativo::getMonto)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        BigDecimal gananciaNeta = precioPactado.subtract(costoVehiculo).subtract(comisionFinal).subtract(totalGastos);
        dto.setGananciaNetaEmpresa(gananciaNeta);

        // Mapear Cliente (COMPLETO)
        if (venta.getCliente() != null) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setIdCliente(venta.getCliente().getIdCliente());
            clienteDTO.setNombreRazonSocial(venta.getCliente().getNombreRazonSocial());
            clienteDTO.setDniCuit(venta.getCliente().getDniCuit());
            clienteDTO.setTelefono(venta.getCliente().getTelefono());
            clienteDTO.setEmail(venta.getCliente().getEmail());
            clienteDTO.setDireccion(venta.getCliente().getDireccion());
            clienteDTO.setLocalidad(venta.getCliente().getLocalidad());
            clienteDTO.setProvincia(venta.getCliente().getProvincia());
            clienteDTO.setCondicionIva(venta.getCliente().getCondicionIva());
            dto.setCliente(clienteDTO);
        }

        // Mapear Empleado (COMPLETO)
        if (venta.getEmpleadoVendedor() != null) {
            EmpleadoDTO empleadoDTO = new EmpleadoDTO();
            empleadoDTO.setIdEmpleado(venta.getEmpleadoVendedor().getIdEmpleado());
            empleadoDTO.setNombreCompleto(venta.getEmpleadoVendedor().getNombreCompleto());
            dto.setEmpleadoVendedor(empleadoDTO);
        }
        
        // Mapear Gastos (COMPLETO)
        if (venta.getGastosOperativosVenta() != null) {
            List<GastoDTO> gastosDTO = venta.getGastosOperativosVenta().stream()
                    .map(gasto -> {
                        GastoDTO gastoDTO = new GastoDTO();
                        gastoDTO.setDescripcion(gasto.getDescripcion());
                        gastoDTO.setMonto(gasto.getMonto());
                        return gastoDTO;
                    }).collect(Collectors.toList());
            dto.setGastosOperativosVenta(gastosDTO);
        }
        
        // Mapear los detalles específicos de la financiación
        if (venta.getDetalleFinanciacionPropia() != null) {
            DetalleFinanciacionPropiaDTO financDTO = new DetalleFinanciacionPropiaDTO();
            financDTO.setMontoFinanciado(venta.getDetalleFinanciacionPropia().getMontoFinanciado());
            financDTO.setCantidadCuotas(venta.getDetalleFinanciacionPropia().getCantidadCuotas());
            financDTO.setValorCuotaAproximado(venta.getDetalleFinanciacionPropia().getValorCuotaAproximado());
            financDTO.setMontoTotalConInteres(venta.getDetalleFinanciacionPropia().getMontoTotalConInteres()); // ✅
            financDTO.setValorCuota(venta.getDetalleFinanciacionPropia().getValorCuota()); // ✅
            financDTO.setCantidadCuotasPagadas(venta.getDetalleFinanciacionPropia().getCantidadCuotasPagadas());
            dto.setDetalleFinanciacionPropia(financDTO);
        }


        if (venta.getDetalleFinanciacionPrendaria() != null) {
            DetalleFinanciacionPrendariaDTO financDTO = new DetalleFinanciacionPrendariaDTO();
            financDTO.setEntidadFinanciera(venta.getDetalleFinanciacionPrendaria().getEntidadFinanciera());
            financDTO.setMontoCreditoOtorgado(venta.getDetalleFinanciacionPrendaria().getMontoCreditoOtorgado());
            financDTO.setPlazoMeses(venta.getDetalleFinanciacionPrendaria().getPlazoMeses());
            // FALTAN ESTOS DOS:
            financDTO.setValorCuotaEstimado(venta.getDetalleFinanciacionPrendaria().getValorCuotaEstimado());
            financDTO.setNumeroContratoORef(venta.getDetalleFinanciacionPrendaria().getNumeroContratoORef());
            dto.setDetalleFinanciacionPrendaria(financDTO);
        }

        if (venta.getDetalleInversion() != null) {
            DetalleInversionDTO inversionDTO = new DetalleInversionDTO();
            inversionDTO.setCodigo(venta.getDetalleInversion().getCodigo());
            inversionDTO.setAnio(venta.getDetalleInversion().getAnio());
            inversionDTO.setPrecio(venta.getDetalleInversion().getPrecio());
            inversionDTO.setCuotas(venta.getDetalleInversion().getCuotas());
            dto.setDetalleInversion(inversionDTO);
        }
        
     // 🆕 Pago principal crédito
        if (venta.getPagoPrincipalCredito() != null) {
            PagoPrincipalCreditoDTO ppc = new PagoPrincipalCreditoDTO();
            ppc.setMontoTotalCredito(venta.getPagoPrincipalCredito().getMontoTotalCredito());
            ppc.setCantidadCuotasCredito(venta.getPagoPrincipalCredito().getCantidadCuotasCredito());
            ppc.setMontoCuotaCredito(venta.getPagoPrincipalCredito().getMontoCuotaCredito());
            dto.setPagoPrincipalCredito(ppc);
        }

        // 🆕 Detalles de pago
        if (venta.getDetallesPago() != null) {
            var lista = venta.getDetallesPago().stream().map(dp -> {
                DetallePagoDTO d = new DetallePagoDTO();
                d.setMetodo(dp.getTipo());                 // "tipo" en entidad -> "metodo" en DTO
                d.setMonto(dp.getMonto());
                d.setReferencia(dp.getObservacion());      // "observacion" en entidad -> "referencia" en DTO
                d.setMontoTotalCredito(dp.getMontoTotalCredito());
                d.setCuotas(dp.getCuotas());
                d.setMontoCuota(dp.getMontoCuota());
                return d;
            }).collect(java.util.stream.Collectors.toList());
            dto.setDetallesPago(lista);
        }
        
        return dto;
    }

    private VentaListDTO convertirVentaAVentaListDTO(Venta venta) {
        VentaListDTO dto = new VentaListDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setFechaOperacion(venta.getFechaOperacion());
        dto.setClasificacionVehiculo(venta.getClasificacionVehiculoVendido());
        dto.setPrecioPactado(venta.getPrecioVehiculoPactado());
        dto.setEstadoOperacion(venta.getEstadoOperacion());
        

        if (venta.getCliente() != null) {
            dto.setNombreCliente(venta.getCliente().getNombreRazonSocial());
        }
        if (venta.getEmpleadoVendedor() != null) {
            dto.setNombreVendedor(venta.getEmpleadoVendedor().getNombreCompleto());
        }
        return dto;
    }
    
    @Transactional
    public void actualizarCantidadCuotasPagadas(Long idVenta, Integer cantidadPagadas) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + idVenta));

        DetalleFinanciacionPropia detalle = venta.getDetalleFinanciacionPropia();
        if (detalle == null) {
            throw new RuntimeException("La venta no tiene financiación propia asociada.");
        }

        detalle.setCantidadCuotasPagadas(cantidadPagadas);
        // No hace falta guardar explícitamente si JPA hace cascading.
        // Pero para asegurar:
        ventaRepository.save(venta);
    }

}
