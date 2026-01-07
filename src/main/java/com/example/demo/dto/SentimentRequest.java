package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.processing.Pattern;

@Data
public class SentimentRequest {

    @NotBlank(message = "El campo 'text' es obligatorio")
    @Size(min = 3, message = "El texto debe tener al menos 3 carácter")

    @Pattern(
            regexp = "^(?=.*[a-zA-ZáéíóúÁÉÍÓÚñÑ])[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s.,!?]+$",
            message = "El texto debe contener letras y no puede ser únicamente numérico"
    )

private String text;
}
