package com.credibanco.swarch;

import com.credibanco.swarch.exceptions.ApiException;
import com.credibanco.swarch.model.EntrustCredentials;
import com.credibanco.swarch.out.IEntrustSecret;
import com.credibanco.swarch.out.ILoggerPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import static com.credibanco.swarch.constant.SecretConstants.*;

@Component
@RequiredArgsConstructor
public class AwsSecretAdapter implements IEntrustSecret {

    private final SecretsManagerClient secretsManagerClient;
    private final ObjectMapper objectMapper;
    private final ILoggerPort loggerService;

    @Override
    public EntrustCredentials retrieveAndParseSecret(String secretName) {
        GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        try {
            GetSecretValueResponse valueResponse = secretsManagerClient.getSecretValue(valueRequest);
            String secretJson = valueResponse.secretString();
            return objectMapper.readValue(secretJson, EntrustCredentials.class);

        } catch (JsonProcessingException e) {
            loggerService.logError(secretName, ADAPTER_LAYER_NAME, PARSER_ERROR_LOG_MESSAGE, e.getMessage());
            throw new ApiException(500, PARSER_ERROR_CODE, PARSER_EXCEPTION_MESSAGE);

        } catch (Exception e) {
            loggerService.logError(secretName, ADAPTER_LAYER_NAME, RETRIEVAL_ERROR_LOG_MESSAGE, e.getMessage());
            throw new ApiException(500, RETRIEVAL_ERROR_CODE, RETRIEVAL_EXCEPTION_MESSAGE);
        }
    }
}
