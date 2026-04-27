package cl.duoc.mantenedorVehiculos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mantenedorVehiculos.dto.ApiResponse;
import cl.duoc.mantenedorVehiculos.dto.VehiculoDTO;
import cl.duoc.mantenedorVehiculos.service.VehiculoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<VehiculoDTO>>> getAllVehiculos(){
    List<VehiculoDTO> vehiculo = vehiculoService.getAllVehiculoDTO();
    ApiResponse<List<VehiculoDTO>> response =
                new ApiResponse<>(200, "Listado de vehiculos", vehiculo);
        return ResponseEntity.ok(response); 
    }
    
}
