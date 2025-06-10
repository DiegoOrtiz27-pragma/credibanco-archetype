package com.credibanco.swarch.websocket;

import com.credibanco.swarch.entity.LoanTraceData;
import com.credibanco.swarch.mapper.ILoanTraceMapper;
import com.credibanco.swarch.model.LoanTrace;
import com.credibanco.swarch.out.ITraceLoanPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import static com.credibanco.swarch.constant.DynamoDBConstatns.TABLE_NAME_LOAN_TRACES;

@Component
@RequiredArgsConstructor
public class LoanTraceRepositoryAdapter implements ITraceLoanPort {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final ILoanTraceMapper mapper;

    private DynamoDbTable<LoanTraceData> getTable() {
        return dynamoDbEnhancedClient.table(TABLE_NAME_LOAN_TRACES, TableSchema.fromBean(LoanTraceData.class));
    }

    @Override
    public void saveTraceLoan(LoanTrace trace) {
        LoanTraceData data = mapper.toData(trace);
        getTable().putItem(data);
    }
}