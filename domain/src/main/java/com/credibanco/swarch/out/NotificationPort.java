package com.credibanco.swarch.out;

public interface NotificationPort {
    /**
     * Envía un mensaje a un usuario específico.
     * @param userId El identificador del usuario/sesión a notificar.
     * @param message El mensaje a enviar.
     */
    void sendNotification(String userId, String message);
}
