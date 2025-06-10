package com.credibanco.swarch.adapter.sqs;

import com.credibanco.swarch.out.ISendMessageToSqsPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Slf4j
@RequiredArgsConstructor
@Component
public class SqsAdapter implements ISendMessageToSqsPort {

    private final SqsClient sqsClient;

    @Value("${aws.sqs.queueUrl}")
    private String queueUrl;

    @Override
    public void sendMessage(String message) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();

        sqsClient.sendMessage(request);
        log.info("Mensaje enviado a SQS: {}", message);
    }
}

