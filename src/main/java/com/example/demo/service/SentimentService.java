package com.example.demo.service;


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
