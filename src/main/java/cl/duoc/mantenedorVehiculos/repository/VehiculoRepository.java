package cl.duoc.mantenedorVehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.mantenedorVehiculos.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
