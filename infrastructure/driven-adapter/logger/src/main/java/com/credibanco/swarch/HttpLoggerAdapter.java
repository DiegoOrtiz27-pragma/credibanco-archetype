package com.credibanco.swarch;

import com.credibanco.swarch.out.ILoggerPort;
import com.credibanco.swarch.valueobject.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Profile("!local")
@RequiredArgsConstructor
public class HttpLoggerAdapter implements ILoggerPort {

    private final WebClient.Builder webClientBuilder;

    @Value("${logging.service.url}")
    private String loggingServiceUrl;

    /**
     * DTO interno para estandarizar el cuerpo del JSON que se envía al servicio de logs.
     */
    private record LogEventDto(String severity, String transactionId, String layer, String message, Object additionalData) {}

    @Override
    public void logInfo(String transactionId, String layer, String message, Object additionalData) {
        sendLogEvent(new LogEventDto("INFO", transactionId, layer, message, additionalData));
    }

    @Override
    public void logWarning(String transactionId, String layer, String message, Object additionalData) {
        // Se implementa siguiendo el mismo patrón, cambiando la severidad.
        sendLogEvent(new LogEventDto("WARN", transactionId, layer, message, additionalData));
    }

    @Override
    public void logError(String transactionId, String layer, String message, Object additionalData) {
        sendLogEvent(new LogEventDto("ERROR", transactionId, layer, message, additionalData));
    }

    @Override
    public void logSuccess(String transactionId, String layer, String message, Object additionalData) {
        // Se implementa con una severidad "SUCCESS" para distinguirlo.
        sendLogEvent(new LogEventDto("SUCCESS", transactionId, layer, message, additionalData));
    }

    @Override
    public void logMessage(MessageType type, String message) {
        // Se mapea el enum MessageType a una severidad en String.
        // Se envían nulos para los campos no disponibles en esta firma.
        String severity = switch (type) {
            case INFO -> "INFO";
            case WARNING -> "WARN";
            case ERROR -> "ERROR";
            case SUCCESS -> "SUCCESS";
        };
        sendLogEvent(new LogEventDto(severity, null, null, message, null));
    }

    /**
     * Método privado y centralizado que realiza la llamada HTTP asíncrona.
     * @param event El DTO con la información del log a enviar.
     */
    private void sendLogEvent(LogEventDto event) {
        webClientBuilder.build()
                .post()
                .uri(loggingServiceUrl)
                .body(Mono.just(event), LogEventDto.class)
                .retrieve()
                .bodyToMono(Void.class)
                // En un escenario real, podríamos tener un mejor manejo de errores,
                // como enviar el log a un fallback (la consola) si la llamada falla.
                .doOnError(e -> System.err.println("Error sending log to logging service: " + e.getMessage()))
                .subscribe(); // Se suscribe para "disparar" la llamada de forma no bloqueante.
    }
}
