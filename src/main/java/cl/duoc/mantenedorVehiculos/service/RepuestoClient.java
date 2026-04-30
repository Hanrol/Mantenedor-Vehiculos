package cl.duoc.mantenedorVehiculos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepuestoClient {

    private final WebClient repuestoWebClient;

    public String consultarRepuestos(){
        return repuestoWebClient.get()
            .uri("/api/v1/repuestos")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    public String consultarRepuestosPorVehiculo(String marca, String modelo) {
        return repuestoWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/repuestos/buscar")
                        .queryParam("marca", marca)
                        .queryParam("modelo", modelo)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

