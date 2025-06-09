package com.credibanco.swarch.mapper;

import com.credibanco.swarch.entity.LoanTraceData;
import com.credibanco.swarch.model.Loan;
import com.credibanco.swarch.model.LoanTrace;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILoanTraceMapper extends GenericDataMapper<LoanTrace, LoanTraceData> {
}