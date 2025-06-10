package com.credibanco.swarch.mapper;

import com.credibanco.swarch.loan.LoanData;
import com.credibanco.swarch.model.Loan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-09T22:50:14-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class LoanDataMapperImpl implements LoanDataMapper {

    @Override
    public Loan toEntity(LoanData arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Loan.LoanBuilder loan = Loan.builder();

        loan.id( arg0.getId() );
        loan.amount( arg0.getAmount() );
        loan.interestRate( arg0.getInterestRate() );
        loan.startDate( arg0.getStartDate() );
        loan.endDate( arg0.getEndDate() );

        return loan.build();
    }

    @Override
    public LoanData toData(Loan arg0) {
        if ( arg0 == null ) {
            return null;
        }

        LoanData loanData = new LoanData();

        loanData.setId( arg0.getId() );
        loanData.setAmount( arg0.getAmount() );
        loanData.setInterestRate( arg0.getInterestRate() );
        loanData.setStartDate( arg0.getStartDate() );
        loanData.setEndDate( arg0.getEndDate() );

        return loanData;
    }
}
