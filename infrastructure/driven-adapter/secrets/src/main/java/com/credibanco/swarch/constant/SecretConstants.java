package com.credibanco.swarch.constant;

public final class SecretConstants {

    private SecretConstants() {
    }

    // --- Propiedades de Configuración ---
    public static final String AWS_REGION_PROPERTY = "${aws.region}";
    public static final String ADAPTER_LAYER_NAME = "AwsSecretAdapter";

    // Mensajes para Logs
    public static final String PARSER_ERROR_LOG_MESSAGE = "Ocurrió un error parseando el JSON del secreto.";
    public static final String RETRIEVAL_ERROR_LOG_MESSAGE = "Error al recuperar el secreto de AWS.";

    // Códigos de Error para Excepciones
    public static final String PARSER_ERROR_CODE = "SECRET_PARSER_ERROR";
    public static final String RETRIEVAL_ERROR_CODE = "SECRET_RETRIEVAL_ERROR";

    // Mensajes de Excepción para el Usuario Final
    public static final String PARSER_EXCEPTION_MESSAGE = "Error al procesar la información de seguridad.";
    public static final String RETRIEVAL_EXCEPTION_MESSAGE = "No se pudo recuperar la información de seguridad.";
}
