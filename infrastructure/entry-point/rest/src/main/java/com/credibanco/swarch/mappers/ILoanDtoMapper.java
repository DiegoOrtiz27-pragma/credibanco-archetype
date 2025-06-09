package com.credibanco.swarch.mappers;


import com.credibanco.swarch.dto.LoanDto;
import com.credibanco.swarch.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILoanDtoMapper {

    Loan loanDtoToLoan(LoanDto loanDTO);

}
