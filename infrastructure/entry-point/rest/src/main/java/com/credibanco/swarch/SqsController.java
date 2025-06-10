package com.credibanco.swarch;

import com.credibanco.swarch.usecase.SendMessageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sqs")
@RequiredArgsConstructor
public class SqsController {

    private final SendMessageUseCase sendMessageUseCase;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody String message) {
        sendMessageUseCase.execute(message);
        return ResponseEntity.ok().build();
    }
}

