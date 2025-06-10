package com.credibanco.swarch.usecase;

import com.credibanco.swarch.out.ISendMessageToSqsPort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SendMessageUseCase {

    private final ISendMessageToSqsPort sendMessageToSqsPort;

    public void execute(String message) {
        sendMessageToSqsPort.sendMessage(message);
    }
}
