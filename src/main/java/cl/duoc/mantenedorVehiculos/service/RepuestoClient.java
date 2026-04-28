package cl.duoc.mantenedorVehiculos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepuestoClient {

    private final WebClient repuestosWebClient;

    public String consultarRepuestos(){
        return repuestosWebClient.get()
            .uri("/api/repuestos")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

}

