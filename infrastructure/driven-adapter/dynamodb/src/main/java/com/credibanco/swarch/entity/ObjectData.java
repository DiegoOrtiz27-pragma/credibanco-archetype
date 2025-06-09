package com.credibanco.swarch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;


@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
public class ObjectData {

    @Getter(onMethod_ = @DynamoDbPartitionKey)
    private String id;

    private String name;
    private String description;
    private String creationDate;
    private String lastUpdateDate;
}
