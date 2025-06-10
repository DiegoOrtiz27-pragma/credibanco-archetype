package com.credibanco.swarch.websocket;

import com.credibanco.swarch.out.NotificationPort;
import com.credibanco.swarch.session.WebSocketSessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketNotification implements NotificationPort {

    private final WebSocketSessionManager sessionManager;

    @Override
    public void sendNotification(String userId, String message) {
        WebSocketSession session = sessionManager.getSession(userId);
        if (session != null && session.isOpen()) {
            try {
                log.info("Sending WebSocket message to user {}: {}", userId, message);
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error("Failed to send WebSocket message to user {}", userId, e);
            }
        } else {
            log.warn("No active WebSocket session found for user {}", userId);
        }
    }
}
