
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime; // Clase para manejar fechas y horas de forma moderna

// Anotaciones de Lombok que "escriben" código por nosotros al compilar
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    // Momento exacto en que ocurre el error
    private LocalDateTime timestamp;

    // Código de estado HTTP
    private int status;

    // Nombre del error
    private String error;

    // Mensaje explicando qué salió mal
    private String message;

    // URL del usuario cuando ffalla
    private String path;