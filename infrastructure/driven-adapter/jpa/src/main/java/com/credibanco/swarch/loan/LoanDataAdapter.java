package com.credibanco.swarch.loan;

import com.credibanco.swarch.generic.AdapterOperations;
import com.credibanco.swarch.mapper.LoanDataMapper;
import com.credibanco.swarch.model.Loan;
import com.credibanco.swarch.out.ILoanPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public abstract class LoanDataAdapter extends AdapterOperations<Loan, LoanData, String, LoanDataRepository, LoanDataMapper>
        implements ILoanPort {


    protected LoanDataAdapter(LoanDataRepository repository, LoanDataMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Optional<Loan> getLoanById(String loanId) {
        return repository.findById(loanId)
                .map(this::toEntity);
    }

}
