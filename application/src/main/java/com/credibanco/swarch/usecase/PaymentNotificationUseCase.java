package com.credibanco.swarch.usecase;

import com.credibanco.swarch.out.NotificationPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class PaymentNotificationUseCase {

    private final NotificationPort notificationPort;

    public void processPaymentNotification(String loanId, BigDecimal paymentAmount) {
        // ... (Lógica para procesar el pago) ...
        // ... (loan.applyPayment(), loanRepository.save(loan), etc.) ...

        // Al final del proceso, el gerente (UseCase) le da la orden al trabajador (Puerto).
        // No sabe ni le importa que esto se vaya a enviar por un WebSocket.
        String userId = "user_del_loan_" + loanId; // Lógica para obtener el userId
        notificationPort.sendNotification(userId, "Tu pago de " + paymentAmount + " ha sido procesado exitosamente.");
    }
}
