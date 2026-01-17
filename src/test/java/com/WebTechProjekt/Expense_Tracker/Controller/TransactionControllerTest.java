package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller-Test für TransactionController.
 * Testet die REST-API-Endpunkte für Transaktionen.
 */
@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @Test
    @WithMockUser
    void testGetTransactions_ShouldReturnTransactionList() throws Exception {
        // Arrange
        List<Transaction> mockTransactions = List.of(
                new Transaction(1, "Groceries", new BigDecimal("50.00"), 
                        LocalDate.now(), Transaction.Type.AUSGABEN, "Weekly shopping", null, "testuser"),
                new Transaction(2, "Salary", new BigDecimal("2000.00"), 
                        LocalDate.now(), Transaction.Type.EINKOMMEN, "Monthly salary", null, "testuser")
        );

        when(transactionService.getAllTransactions()).thenReturn(mockTransactions);

        // Act & Assert
        mockMvc.perform(get("/et/transactions")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Groceries"))
                .andExpect(jsonPath("$[1].title").value("Salary"));
    }

    @Test
    @WithMockUser
    void testCreateTransaction_ShouldReturnCreatedTransaction() throws Exception {
        // Arrange
        Transaction inputTransaction = new Transaction();
        inputTransaction.setTitle("Coffee");
        inputTransaction.setAmount(new BigDecimal("4.50"));
        inputTransaction.setType(Transaction.Type.AUSGABEN);
        inputTransaction.setDate(LocalDate.now());

        Transaction savedTransaction = new Transaction();
        savedTransaction.setId(1);
        savedTransaction.setTitle("Coffee");
        savedTransaction.setAmount(new BigDecimal("4.50"));
        savedTransaction.setType(Transaction.Type.AUSGABEN);
        savedTransaction.setDate(LocalDate.now());

        when(transactionService.saveTransaction(any(Transaction.class))).thenReturn(savedTransaction);

        // Act & Assert
        mockMvc.perform(post("/et/transactions")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputTransaction)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Coffee"))
                .andExpect(jsonPath("$.amount").value(4.50));
    }

    @Test
    @WithMockUser
    void testGetTransactions_WhenEmpty_ShouldReturnEmptyList() throws Exception {
        // Arrange
        when(transactionService.getAllTransactions()).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/et/transactions")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @WithMockUser
    void testGetBalance_ShouldReturnTotalBalance() throws Exception {
        // Arrange
        BigDecimal mockBalance = new BigDecimal("1500.00");
        when(transactionService.getTotal()).thenReturn(mockBalance);

        // Act & Assert
        mockMvc.perform(get("/et/transactions/Balance")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("1500.00"));
    }
}

