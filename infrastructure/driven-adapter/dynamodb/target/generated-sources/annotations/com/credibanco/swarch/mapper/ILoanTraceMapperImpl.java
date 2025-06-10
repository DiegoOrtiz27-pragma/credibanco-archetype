package com.credibanco.swarch.mapper;

import com.credibanco.swarch.entity.LoanTraceData;
import com.credibanco.swarch.model.LoanTrace;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-09T22:50:30-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ILoanTraceMapperImpl implements ILoanTraceMapper {

    @Override
    public LoanTrace toEntity(LoanTraceData arg0) {
        if ( arg0 == null ) {
            return null;
        }

        LoanTrace.LoanTraceBuilder loanTrace = LoanTrace.builder();

        return loanTrace.build();
    }

    @Override
    public LoanTraceData toData(LoanTrace arg0) {
        if ( arg0 == null ) {
            return null;
        }

        LoanTraceData loanTraceData = new LoanTraceData();

        return loanTraceData;
    }
}
