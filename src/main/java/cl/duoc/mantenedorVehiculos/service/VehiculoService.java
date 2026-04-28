package cl.duoc.mantenedorVehiculos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.mantenedorVehiculos.dto.ApiResponse;
import cl.duoc.mantenedorVehiculos.dto.VehiculoDTO;
import cl.duoc.mantenedorVehiculos.model.Vehiculo;
import cl.duoc.mantenedorVehiculos.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    private boolean marcaValida(String marca) {
        return marca.equalsIgnoreCase("BMW") ||
            marca.equalsIgnoreCase("Audi") ||
            marca.equalsIgnoreCase("Mercedes");
    }

    public List<VehiculoDTO> getAllVehiculoDTO(){

        return vehiculoRepository.findAll()
            .stream()
            .map(vehiculo -> new VehiculoDTO(
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getAnnioFabricacion(),
                vehiculo.getColor(),
                vehiculo.getStock()
            ))
            .toList();
    }

    public ApiResponse<VehiculoDTO> agregarVehiculo(VehiculoDTO vehiculoDTO) {

        if (!marcaValida(vehiculoDTO.getMarca())) {
            return new ApiResponse<>(
                    400,
                    "Marca no permitida. Solo se permiten BMW, Audi y Mercedes",
                    null
            );
        }

        Optional<Vehiculo> vehiculoExistente = vehiculoRepository
                .findByMarcaIgnoreCaseAndModeloIgnoreCase(
                        vehiculoDTO.getMarca(),
                        vehiculoDTO.getModelo()
                );

        if (vehiculoExistente.isPresent()) {
            return new ApiResponse<>(
                    400,
                    "El vehículo ya existe. Para aumentar stock usa el endpoint de agregar stock",
                    null
            );
        }

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(vehiculoDTO.getMarca());
        vehiculo.setModelo(vehiculoDTO.getModelo());
        vehiculo.setAnnioFabricacion(vehiculoDTO.getAnnioFabricacion());
        vehiculo.setColor(vehiculoDTO.getColor());
        vehiculo.setStock(vehiculoDTO.getStock());

        Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculo);

        VehiculoDTO respuesta = new VehiculoDTO(
                vehiculoGuardado.getMarca(),
                vehiculoGuardado.getModelo(),
                vehiculoGuardado.getAnnioFabricacion(),
                vehiculoGuardado.getColor(),
                vehiculoGuardado.getStock()
        );

        return new ApiResponse<>(201, "Vehículo agregado correctamente", respuesta);
    }

    public ApiResponse<Long> verStockDisponible(Long id) {

        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(id);

        if (vehiculoOptional.isEmpty()) {
            return new ApiResponse<>(404, "Vehículo no encontrado", null);
        }

        Vehiculo vehiculo = vehiculoOptional.get();

        return new ApiResponse<>(
                200,
                "Stock disponible del vehículo " + vehiculo.getMarca() + " " + vehiculo.getModelo(),
                vehiculo.getStock()
        );
    }

    public ApiResponse<VehiculoDTO> venderVehiculo(Long id, Long cantidad) {

        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(id);

        if (vehiculoOptional.isEmpty()) {
            return new ApiResponse<>(404, "Vehículo no encontrado", null);
        }

        if (cantidad <= 0) {
            return new ApiResponse<>(400, "La cantidad a vender debe ser mayor a 0", null);
        }

        Vehiculo vehiculo = vehiculoOptional.get();

        if (vehiculo.getStock() < cantidad) {
            return new ApiResponse<>(400, "Stock insuficiente", null);
        }

        vehiculo.setStock(vehiculo.getStock() - cantidad);

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);

        VehiculoDTO respuesta = new VehiculoDTO(
                vehiculoActualizado.getMarca(),
                vehiculoActualizado.getModelo(),
                vehiculoActualizado.getAnnioFabricacion(),
                vehiculoActualizado.getColor(),
                vehiculoActualizado.getStock()
        );

        return new ApiResponse<>(200, "Venta realizada correctamente", respuesta);
    }

    public ApiResponse<VehiculoDTO> agregarStock(Long id, Long cantidad) {

        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(id);

        if (vehiculoOptional.isEmpty()) {
            return new ApiResponse<>(404, "Vehículo no encontrado", null);
        }

        if (cantidad <= 0) {
            return new ApiResponse<>(400, "La cantidad a agregar debe ser mayor a 0", null);
        }

        Vehiculo vehiculo = vehiculoOptional.get();

        vehiculo.setStock(vehiculo.getStock() + cantidad);

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);

        VehiculoDTO respuesta = new VehiculoDTO(
                vehiculoActualizado.getMarca(),
                vehiculoActualizado.getModelo(),
                vehiculoActualizado.getAnnioFabricacion(),
                vehiculoActualizado.getColor(),
                vehiculoActualizado.getStock()
        );

        return new ApiResponse<>(200, "Stock agregado correctamente", respuesta);
    }
}
