package com.credibanco.swarch.constant;

public class DynamoDBConstatns {

    private DynamoDBConstatns() {
    }

    // --- Nombres de Tablas ---
    public static final String TABLE_NAME_LOAN_TRACES = "LoanTraces";

    // --- Propiedades de Configuración (.yml) ---
    public static final String PROPERTY_ENDPOINT = "${aws.dynamodb.endpoint}";
    public static final String PROPERTY_REGION = "${aws.region}";

}
