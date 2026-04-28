package cl.duoc.mantenedorVehiculos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mantenedorVehiculos.dto.ApiResponse;
import cl.duoc.mantenedorVehiculos.dto.VehiculoDTO;
import cl.duoc.mantenedorVehiculos.service.RepuestoClient;
import cl.duoc.mantenedorVehiculos.service.VehiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;
    private final RepuestoClient repuestoClient;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<VehiculoDTO>>> getAllVehiculos(){
    List<VehiculoDTO> vehiculo = vehiculoService.getAllVehiculoDTO();

    ApiResponse<List<VehiculoDTO>> response =
                new ApiResponse<>(200, "Listado de vehiculos", vehiculo);
                
        return ResponseEntity.ok(response); 
    }
    
    @PostMapping("/añadir")
    public ResponseEntity<ApiResponse<VehiculoDTO>> agregarVehiculo(@Valid @RequestBody VehiculoDTO vehiculoDTO) {
        ApiResponse<VehiculoDTO> response = vehiculoService.agregarVehiculo(vehiculoDTO);

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<ApiResponse<Long>> verStockDisponible(@PathVariable Long id) {
        ApiResponse<Long> response = vehiculoService.verStockDisponible(id);

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/vender/{id}/{cantidad}")
    public ResponseEntity<ApiResponse<VehiculoDTO>> venderVehiculo(@PathVariable Long id, @PathVariable Long cantidad) {
        ApiResponse<VehiculoDTO> response = vehiculoService.venderVehiculo(id, cantidad);

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/stock/añadir/{id}/{cantidad}")
    public ResponseEntity<ApiResponse<VehiculoDTO>> agregarStock(@PathVariable Long id, @PathVariable Long cantidad) {
        ApiResponse<VehiculoDTO> response = vehiculoService.agregarStock(id, cantidad);

        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/consultar-repuestos")
    public String consultarRespuestos(){
        return repuestoClient.consultarRepuestos();
    }
}
