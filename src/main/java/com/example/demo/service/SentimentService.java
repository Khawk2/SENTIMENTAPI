package com.example.demo.service;


import com.example.demo.dto.SentimentRequest;
import com.example.demo.dto.SentimentResponse;
import com.example.demo.model.SentimentAnalysis;
import com.example.demo.repository.SentimentAnalysisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SentimentService {

    private final SentimentAnalysisRepository repository;

    @Value("${ml.service.url:http://ml-service:8000}")
    private String mlServiceUrl;

    public SentimentResponse analyzeSentiment(SentimentRequest request) {
        log.info("Analizando sentimiento para texto: {}", request.getText().substring(0, Math.min(50, request.getText().length())));

        try {
            log.debug("Enviando petición a ML service: {}", mlServiceUrl + "/predict");
            log.debug("Body de la petición: text={}", request.getText());

            // Preparar headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(java.util.Collections.singletonList(MediaType.APPLICATION_JSON));

            // Crear el body como Map para serialización JSON
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("text", request.getText());

            // Crear HttpEntity con headers y body
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

            // Llamar al servicio de ML usando RestTemplate
            ResponseEntity<MlServiceResponse> response = restTemplate.exchange(
                    mlServiceUrl + "/predict",
                    HttpMethod.POST,
                    entity,
                    MlServiceResponse.class
            );

            MlServiceResponse mlResponse = response.getBody();

            if (mlResponse == null) {
                throw new RuntimeException("No se recibió respuesta del servicio de ML");
            }

            // Guardar en base de datos
            SentimentAnalysis analysis = new SentimentAnalysis();
            analysis.setText(request.getText());
            analysis.setPrevision(mlResponse.getPrevision());
            analysis.setProbabilidad(mlResponse.getProbabilidad());
            repository.save(analysis);

            log.info("Predicción guardada: {} (probabilidad: {})",
                    mlResponse.getPrevision(), mlResponse.getProbabilidad());

            return new SentimentResponse(
                    mlResponse.getPrevision(),
                    mlResponse.getProbabilidad()
            );

        } catch (Exception e) {
            log.error("Error al analizar sentimiento: {}", e.getMessage(), e);
            throw new RuntimeException("Error al procesar el análisis de sentimiento: " + e.getMessage(), e);
        }

import com.example.demo.model.SentimentRequest;
import com.example.demo.model.SentimentResponse;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {

    public SentimentResponse analyze(SentimentRequest solicitud) {
        String analisisTexto = solicitud.getText(); //se debe obtener solicitud del texto


    //modelo de data science
    String prevision = "Negativo";
    double probabilidad = 9.91;

    return new SentimentResponse (prevision, probabilidad);

    }
}
