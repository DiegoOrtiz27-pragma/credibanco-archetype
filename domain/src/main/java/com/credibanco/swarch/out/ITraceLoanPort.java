package com.credibanco.swarch.out;


import com.credibanco.swarch.model.LoanTrace;

public interface ITraceLoanPort {
    void saveTraceLoan(LoanTrace loanTrace);
}
