package com.credibanco.swarch.mapper;

import com.credibanco.swarch.loan.LoanData;
import com.credibanco.swarch.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LoanDataMapper extends GenericDataMapper<Loan, LoanData> {
}
