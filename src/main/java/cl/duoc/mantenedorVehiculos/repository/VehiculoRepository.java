package cl.duoc.mantenedorVehiculos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.mantenedorVehiculos.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    Optional<Vehiculo> findByMarcaIgnoreCaseAndModeloIgnoreCase(String marca, String modelo);

}
