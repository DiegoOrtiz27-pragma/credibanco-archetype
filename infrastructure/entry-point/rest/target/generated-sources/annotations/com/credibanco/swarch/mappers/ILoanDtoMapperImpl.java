package com.credibanco.swarch.mappers;

import com.credibanco.swarch.dto.LoanDto;
import com.credibanco.swarch.model.Loan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-09T22:50:36-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class ILoanDtoMapperImpl implements ILoanDtoMapper {

    @Override
    public Loan loanDtoToLoan(LoanDto loanDTO) {
        if ( loanDTO == null ) {
            return null;
        }

        Loan.LoanBuilder loan = Loan.builder();

        loan.id( loanDTO.getId() );
        loan.amount( loanDTO.getAmount() );
        loan.interestRate( loanDTO.getInterestRate() );
        loan.startDate( loanDTO.getStartDate() );
        loan.endDate( loanDTO.getEndDate() );

        return loan.build();
    }
}
