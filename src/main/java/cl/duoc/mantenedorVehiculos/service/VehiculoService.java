package cl.duoc.mantenedorVehiculos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.mantenedorVehiculos.dto.VehiculoDTO;
import cl.duoc.mantenedorVehiculos.repository.VehiculoRepository;
import lombok.*;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public List<VehiculoDTO> getAllVehiculoDTO(){

        return vehiculoRepository.findAll()
            .stream()
            .map(vehiculo -> new VehiculoDTO(
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnnioFabricacion(),
                vehiculo.getColor()
            ))
            .toList();
    }
}
