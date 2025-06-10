package com.credibanco.swarch.handler;

import com.credibanco.swarch.session.WebSocketSessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
@RequiredArgsConstructor
public class StatusUpdateSocketHandler extends TextWebSocketHandler {

    private final WebSocketSessionManager sessionManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Asumimos que el userId viene como un parámetro en la URL de conexión, ej: /ws/updates?userId=123
        String userId = (String) session.getAttributes().get("userId");
        if (userId != null) {
            log.info("WebSocket connection established for user: {}", userId);
            sessionManager.addSession(userId, session);
        } else {
            log.warn("WebSocket connection established without userId. Closing session.");
            session.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = (String) session.getAttributes().get("userId");
        if (userId != null) {
            log.info("WebSocket connection closed for user: {}", userId);
            sessionManager.removeSession(userId);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Aquí podríamos procesar mensajes que el cliente envía al servidor
        log.info("Received message from client {}: {}", session.getAttributes().get("userId"), message.getPayload());
    }
}
