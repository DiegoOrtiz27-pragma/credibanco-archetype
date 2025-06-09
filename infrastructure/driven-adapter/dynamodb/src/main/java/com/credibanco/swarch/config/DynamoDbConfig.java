package com.credibanco.swarch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

import static com.credibanco.swarch.constant.DynamoDBConstatns.PROPERTY_ENDPOINT;
import static com.credibanco.swarch.constant.DynamoDBConstatns.PROPERTY_REGION;

@Configuration
public class DynamoDbConfig {

    @Value(PROPERTY_ENDPOINT)
    private String dynamoDbEndpointUrl;

    @Value(PROPERTY_REGION)
    private String awsRegion;

    @Bean
    public DynamoDbClient getDynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.of(awsRegion))
                .endpointOverride(URI.create(dynamoDbEndpointUrl)) // Perfecto para usar DynamoDB Local
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }
}
