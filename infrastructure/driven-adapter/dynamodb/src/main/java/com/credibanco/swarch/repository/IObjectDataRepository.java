package com.credibanco.swarch.repository;

import com.credibanco.swarch.entity.LoanTraceData;

import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IObjectDataRepository extends DynamoDBCrudRepository<LoanTraceData, String> {
}
