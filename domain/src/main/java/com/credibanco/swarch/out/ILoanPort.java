package com.credibanco.swarch.out;



import com.credibanco.swarch.model.Loan;

import java.util.Optional;

public interface ILoanPort {

    Loan save(Loan loan);

    Optional<Loan> getLoanById(String loanId);

}
