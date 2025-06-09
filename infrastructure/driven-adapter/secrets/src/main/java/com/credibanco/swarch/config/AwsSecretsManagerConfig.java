package com.credibanco.swarch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

import static com.credibanco.swarch.constant.SecretConstants.AWS_REGION_PROPERTY;

@Configuration
public class AwsSecretsManagerConfig {

    @Value(AWS_REGION_PROPERTY) // La región de AWS se configura en application.yml
    private String awsRegion;

    @Bean
    public SecretsManagerClient secretsManagerClient() {
        // El SDK buscará las credenciales automáticamente en el entorno (variables, rol IAM, etc.)
        // Esto elimina la necesidad de manejar client_id/client_secret en el código.
        return SecretsManagerClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
