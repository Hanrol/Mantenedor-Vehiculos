package cl.duoc.mantenedorVehiculos.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {

    @NotBlank(message = "La marca no puede estar vacía")
    @Size(min = 3, message = "La marca tener al menos 3 caracteres")
    String marca;

    @NotBlank(message = "El modelo no puede estar vacio")
    @Size(min = 4, message = "La modelo debe tener al menos 4 caracteres")
    String modelo;

    @NotNull(message = "El año no puede ser nulo")
    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
    @Max(value = 2030, message = "El año debe ser menor o igual a 2030")
    Integer annioFabricacion;

    @NotBlank(message = "El color no puede estar vacío")
    @Size(min = 3, message = "El color debe tener al menos 4 caracteres")
    String color;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    Long stock;
}
