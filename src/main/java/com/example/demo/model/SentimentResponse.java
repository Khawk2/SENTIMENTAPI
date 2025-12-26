package com.example.demo.model;

public class SentimentResponse {
    public SentimentResponse(String prevision, double probabilidad) {
    }


    public static class analyze extends SentimentResponse {
        public analyze(SentimentRequest solicitud) {
            super();
        }
    }
}
