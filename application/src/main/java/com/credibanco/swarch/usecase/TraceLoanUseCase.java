package com.credibanco.swarch.usecase;



import com.credibanco.swarch.model.LoanTrace;
import com.credibanco.swarch.out.ITraceLoanPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceLoanUseCase {

    private final ITraceLoanPort iTraceLoanPort;

    public void saveTraceLoan(String loanId, Double amount, String transactionId) {
        iTraceLoanPort.saveTraceLoan(buildLoan(loanId, amount, transactionId));
    }

    private LoanTrace buildLoan(String loanId, Double amount, String transactionId) {
        return LoanTrace.builder()
                .loanId(loanId)
                .amount(amount)
                .transactionId(transactionId)
                .build();
    }

}
