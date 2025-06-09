package com.credibanco.swarch.repository;

import com.credibanco.swarch.entity.ObjectData;
import io.awspring.cloud.dynamodb.DynamoDbRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IObjectDataRepository extends DynamoDbRepository<ObjectData, String> {
}
