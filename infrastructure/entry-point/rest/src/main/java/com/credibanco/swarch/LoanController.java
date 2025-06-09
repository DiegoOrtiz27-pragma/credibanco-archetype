package com.credibanco.swarch;


import com.credibanco.swarch.constant.SwaggerConstants;
import com.credibanco.swarch.documentation.CommonApiResponses;
import com.credibanco.swarch.dto.ApiResponseDto;
import com.credibanco.swarch.dto.LoanDto;
import com.credibanco.swarch.dto.ResponseFactory;
import com.credibanco.swarch.mappers.ILoanDtoMapper;
import com.credibanco.swarch.model.Loan;
import com.credibanco.swarch.out.ILoggerPort;
import com.credibanco.swarch.usecase.LoanUseCase;
import com.credibanco.swarch.usecase.MessageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import static com.credibanco.swarch.constant.RestConstants.*;


/**
 * Controlador REST para manejar operaciones relacionadas con préstamos.
 * <p>
 * Esta clase expone endpoints para crear y gestionar préstamos en la
 * aplicación.
 */
@RestController
@RequestMapping(PATH_LOAN_CONTROLLER)
@RequiredArgsConstructor
@Slf4j
public class LoanController {

    private final LoanUseCase loanUseCase;
    private final ILoanDtoMapper iLoanDtoMapper;

    /**
     * Fábrica de respuestas para centralizar la creación de respuestas exitosas y de error.
     */
    private final ResponseFactory responseFactory;
    /**
     * Servicio de mensajes para obtener textos internacionalizados a partir de llaves.
     */
    private final MessageUseCase messageUseCase;
    private final ILoggerPort loggerService;

    /**
     * Crea un nuevo préstamo en el sistema.
     * <p>
     * Este endpoint recibe los detalles de un préstamo y lo guarda en la base de datos.
     * </p>
     *
     * @param loanDTO el objeto {@link LoanDto} que contiene los detalles del préstamo a crear
     * @return una respuesta con el préstamo creado y el estado HTTP correspondiente
     */
    @PostMapping
    @Operation(summary = SwaggerConstants.SWAGGER_OPERATION_CREATE_LOGAN)
    @CommonApiResponses
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponseDto<Loan>> createLoan(@Valid @RequestBody LoanDto loanDTO, WebRequest request,
                                                           @RequestHeader(name = "transactionId", required = false) String transactionId) {
        loggerService.logInfo(transactionId, LAYER_INFRASTRUCTURE_CONTROLLER_CREATE_LOAN,
                CREATE_LOAN_PROCESS_STARTED, loanDTO);
        var result = loanUseCase.createLoan(iLoanDtoMapper.loanDtoToLoan(loanDTO), transactionId);
        var successMessage = messageUseCase.getMessage(MESSAGE_KEY_CREATION_SUCCESS);
        return ResponseEntity.ok(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, result, transactionId));
    }

    /**
     * Obtiene los detalles de un préstamo por su ID.
     * <p>
     * Este endpoint busca un préstamo en la base de datos utilizando su ID.
     * </p>
     *
     * @param loanId el ID del préstamo a buscar
     * @return una respuesta con los detalles del préstamo y el estado HTTP
     * correspondiente
     */
    @GetMapping("/{id}")
    @Operation(summary = SwaggerConstants.SWAGGER_OPERATION_GET_LOAN_BY_ID)
    @CommonApiResponses
    public ResponseEntity<ApiResponseDto<Loan>> getLoanById(@PathVariable("id") String loanId, WebRequest request,
                                                            @RequestHeader(name = "transactionId",required = false) String transactionId) {
        loggerService.logInfo(transactionId, LAYER_INFRASTRUCTURE_CONTROLLER_GET_LOAN_BY_ID,
                GET_LOAN_BY_ID_PROCESS_STARTED, loanId);
        var loanResponse = loanUseCase.findById(loanId, transactionId);
        String successMessage = messageUseCase.getMessage(MESSAGE_KEY_GET_SUCCESS);
        return ResponseEntity.ok(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId));
    }

}
