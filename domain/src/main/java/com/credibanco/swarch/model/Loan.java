package com.credibanco.swarch.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    private String id;
    private Double amount;
    private Double interestRate;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public String toString() {
        return "LoanSqlModel{" +
                "loanId='" + id + '\'' +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}
