package com.credibanco.swarch.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanTrace {

    private String loanId;
    private Double amount;
    private String transactionId;


    @Override
    public String toString() {
        return "LoanNoSqlModel{" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
