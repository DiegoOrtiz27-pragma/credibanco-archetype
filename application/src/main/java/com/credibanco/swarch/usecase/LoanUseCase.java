package com.credibanco.swarch.usecase;



import com.credibanco.swarch.constant.DomainConstants;
import com.credibanco.swarch.exceptions.ApiException;
import com.credibanco.swarch.model.Loan;
import com.credibanco.swarch.out.ILoanPort;
import com.credibanco.swarch.out.ILoggerBuilderPort;
import com.credibanco.swarch.services.LoanService;
import lombok.RequiredArgsConstructor;

import static com.credibanco.swarch.constant.DomainConstants.*;


/**
 * Caso de uso para crear un nuevo préstamo.
 * <p>
 * Esta clase encapsula la lógica necesaria para crear un préstamo utilizando el servicio {@link LoanService}.
 */
@RequiredArgsConstructor
public class LoanUseCase {

    private final TraceLoanUseCase traceLoanUseCase;
    private final ILoanPort iLoanPort;
    private final ILoggerBuilderPort iLoggerBuilderPort;
    private final AuthenticationUseCase authenticationUseCase;
    private final LoanService loanService;


    public static final double TRACE_LOAN_THRESHOLD = 10.5;

    /**
     * Ejecuta el caso de uso para crear un préstamo.
     *
     * @param loan el DTO que contiene los datos del préstamo a crear
     * @return Loan el objeto {@link Loan} creado
     */
    //@Transactional
    public Loan createLoan(Loan loan, String transactionId) {
        authenticationUseCase.validateTokenUser(UTILITY_EXAMPLE_USER_NAME_VALUE, transactionId); /*El valor que envio por parametro como userName un ejemplo*/
        validateLoanExist(loan.getId(), transactionId);
        validateSaveTraceLoan(loan, transactionId);
        loanService.validateLoanDate(loan);
        return iLoanPort.save(loan);
    }

    /**
     * Obtiene los detalles de un préstamo por su ID.
     *
     * @param loanId el ID del préstamo a buscar
     * @return el objeto {@link Loan} encontrado
     */
    //@Transactional
    public Loan findById(String loanId, String transactionId) {
        return iLoanPort.getLoanById(loanId).orElseThrow(() ->
                new ApiException(404, "404 NOT_FOUND", MESSAGE_KEY_LOAN_NOT_FOUND_ERROR,
                        iLoggerBuilderPort.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_ID_NOT_FOUND,
                                DomainConstants.MESSAGE_LOG_LOAN_ID_NOT_FOUND, transactionId)));
    }

    private void validateSaveTraceLoan(Loan loan, String transactionId) {
        if (loan.getAmount().equals(TRACE_LOAN_THRESHOLD)) {
            traceLoanUseCase.saveTraceLoan(loan.getId(), loan.getAmount(), transactionId);
        }
    }

    private void validateLoanExist(String loanId, String transactionId) {
        if (iLoanPort.getLoanById(loanId).isPresent()) {
            throw new ApiException(400, "400 BAD_REQUEST", MESSAGE_KEY_LOAN_FOUND_ERROR,
                    iLoggerBuilderPort.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_EXIST_ERROR,
                            MESSAGE_LOG_LOAN_EXIST_ERROR, transactionId));
        }
    }



}
