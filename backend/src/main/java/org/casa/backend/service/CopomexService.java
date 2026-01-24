package org.casa.backend.service;

import org.casa.backend.dto.CopomexData;
import org.casa.backend.dto.CopomexResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CopomexService {

    @Value("${copomex.base-url}")
    private String baseUrl;

    @Value("${copomex.token}")
    private String token;

    private final RestTemplate restTemplate = new RestTemplate();

    public CopomexData buscarPorCp(String cp) {

        String url = baseUrl + cp + "?token=" + token;
        System.out.println("Copomex URL => " + url);

        ResponseEntity<List<CopomexResponse>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<CopomexResponse>>() {}
                );

        List<CopomexResponse> lista = response.getBody();

        if (lista == null || lista.isEmpty()) {
            throw new RuntimeException("CP no encontrado");
        }

        return lista.get(0).getResponse();
    }
}
