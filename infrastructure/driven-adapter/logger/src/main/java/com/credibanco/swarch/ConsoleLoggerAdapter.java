package com.credibanco.swarch;

import com.credibanco.swarch.out.ILoggerPort;
import com.credibanco.swarch.valueobject.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
@Slf4j
public class ConsoleLoggerAdapter implements ILoggerPort {

    @Override
    public void logInfo(String transactionId, String layer, String message, Object additionalData) {
        log.info("[INFO] txId={} layer={} message='{}' data={}", transactionId, layer, message, additionalData);
    }

    @Override
    public void logWarning(String transactionId, String layer, String message, Object additionalData) {
        log.warn("[WARN] txId={} layer={} message='{}' data={}", transactionId, layer, message, additionalData);
    }

    @Override
    public void logError(String transactionId, String layer, String message, Object additionalData) {
        log.error("[ERROR] txId={} layer={} message='{}' data={}", transactionId, layer, message, additionalData);
    }

    @Override
    public void logSuccess(String transactionId, String layer, String message, Object additionalData) {
        log.info("[SUCCESS] txId={} layer={} status=SUCCESS message='{}' data={}", transactionId, layer, message, additionalData);
    }

    @Override
    public void logMessage(MessageType type, String message) {
        // Mapeamos nuestro enum del dominio a los métodos de logging correspondientes.
        switch (type) {
            case INFO -> log.info(message);
            case WARNING -> log.warn(message);
            case ERROR -> log.error(message);
            case SUCCESS -> log.info("SUCCESS: {}", message);
            default -> log.debug(message); // Por defecto, usamos un nivel bajo como debug.
        }
    }
}