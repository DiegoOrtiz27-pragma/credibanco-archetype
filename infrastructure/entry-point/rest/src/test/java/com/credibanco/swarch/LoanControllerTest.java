package com.credibanco.swarch;


import com.credibanco.swarch.dto.ApiResponseDto;
import com.credibanco.swarch.dto.LoanDto;
import com.credibanco.swarch.dto.ResponseFactory;
import com.credibanco.swarch.mappers.ILoanDtoMapper;
import com.credibanco.swarch.model.Loan;
import com.credibanco.swarch.out.ILoggerPort;
import com.credibanco.swarch.usecase.LoanUseCase;
import com.credibanco.swarch.usecase.MessageUseCase;
import com.credibanco.swarch.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LoanControllerTest {

    @InjectMocks
    private LoanController loanController;

    @Mock
    private LoanUseCase loanUseCase;
    @Mock
    private ResponseFactory responseFactory;
    @Mock
    private MessageUseCase messageUseCase;
    @Mock
    private ILoggerPort iLoggerPort;

    @Mock
    private ILoanDtoMapper mapper;

    @BeforeEach
    void setUp() {
        Mockito.when(mapper.loanDtoToLoan(any())).thenReturn(TestUtil.buildLoan());
    }

    /**
     * Prueba para el endpoint POST /loans (crear préstamo).
     */
    @Test
    @Order(1)
    @DisplayName("Prueba exitosa de creación de préstamo")
    void createLoanSuccess() {
        // Arrange
        LoanDto loanDTO = LoanDto.builder()
                .id("123")
                .amount(1000.0)
                .interestRate(5.0)
                .startDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        var loan = Loan.builder()
                .id("123")
                .amount(1000.0)
                .interestRate(5.0)
                .startDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        WebRequest mockRequest = mock(WebRequest.class);
        String transactionId = "transaction-123";
        String successMessage = "Préstamo creado exitosamente";
        Mockito.when(loanUseCase.createLoan(Mockito.any(), Mockito.anyString())).thenReturn(loan);
        when(messageUseCase.getMessage(anyString())).thenReturn(successMessage);
        when(responseFactory.createSuccessResponse(any(), any(), any(), any()))
                .thenReturn(new ApiResponseDto<>("200", "Success", loanDTO, transactionId));

        // Act
        ResponseEntity<?> response = loanController.createLoan(loanDTO, mockRequest, transactionId);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(loanUseCase).createLoan(Mockito.any(), Mockito.anyString());
        verify(responseFactory).createSuccessResponse(eq(HttpStatus.OK), eq(successMessage), eq(loan), eq(transactionId));
    }

    /**
     * Prueba para el endpoint GET /loans/{id} (consultar préstamo por ID exitoso).
     */
    @Test
    @Order(2)
    @DisplayName("Debería devolver un préstamo existente por ID")
    void getLoanById() {
        // Arrange: Configuración inicial
        String loanId = "123";
        String transactionId = "txn-456";
        Loan loanResponse = Loan.builder()
                .id("123")
                .amount(1000.0)
                .interestRate(5.0)
                .startDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();

        String successMessage = "Préstamo obtenido exitosamente";

        WebRequest mockRequest = mock(WebRequest.class);

        when(loanUseCase.findById(loanId, transactionId)).thenReturn(loanResponse);
        when(messageUseCase.getMessage(anyString())).thenReturn(successMessage);
        when(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId))
                .thenReturn(new ApiResponseDto<>("200", "Success", loanResponse, transactionId));

        // Act: Ejecución del método que estamos probando
        ResponseEntity<?> response = loanController.getLoanById(loanId, mockRequest, transactionId);

        // Assert: Verificación de resultados esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        verify(iLoggerPort).logInfo(eq(transactionId), eq("infrastructure.controller.LoanController.getLoanById"),
                eq("Se inició la obtención de un prestamo"), eq(loanId));
        verify(loanUseCase).findById(loanId, transactionId);
        verify(messageUseCase).getMessage(anyString());
        verify(responseFactory).createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId);
    }

}
