package com.example.demo.controller;


import com.example.demo.model.SentimentRequest;
import com.example.demo.model.SentimentResponse;
import com.example.demo.service.SentimentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/sentiment")

public class SentimentController {

    private final SentimentService servicioSentimiento;

    public SentimentController (SentimentService servicioSentimiento){
        this.servicioSentimiento = servicioSentimiento;
            }

    @PostMapping
    public ResponseEntity<SentimentResponse> analyzeSentiment(@Validated @RequestBody SentimentRequest solicitud){
        SentimentResponse respuesta = servicioSentimiento.analyze(solicitud);

        return ResponseEntity.ok(respuesta);

    }
}
