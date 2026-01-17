package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Repository.TransactionRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import com.WebTechProjekt.Expense_Tracker.util.SecurityUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Service-Test für TransactionService.
 * Testet die Business-Logik für Transaktionen mit gemocktem Repository.
 */
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        testTransaction = new Transaction();
        testTransaction.setId(1);
        testTransaction.setTitle("Test Transaction");
        testTransaction.setAmount(new BigDecimal("100.00"));
        testTransaction.setDate(LocalDate.now());
        testTransaction.setType(Transaction.Type.AUSGABEN);
        testTransaction.setOwner("testuser");
    }

    @Test
    void testSaveTransaction_ShouldSetOwnerAndSave() {
        // Arrange
        Transaction newTransaction = new Transaction();
        newTransaction.setTitle("New Transaction");
        newTransaction.setAmount(new BigDecimal("50.00"));
        newTransaction.setType(Transaction.Type.EINKOMMEN);

        when(transactionRepo.save(any(Transaction.class))).thenReturn(newTransaction);

        // Act
        try (MockedStatic<SecurityUtils> mockedSecurity = mockStatic(SecurityUtils.class)) {
            mockedSecurity.when(SecurityUtils::getCurrentUsername).thenReturn("currentuser");
            
            Transaction result = transactionService.saveTransaction(newTransaction);

            // Assert
            assertNotNull(result);
            assertEquals("currentuser", newTransaction.getOwner());
            verify(transactionRepo, times(1)).save(newTransaction);
        }
    }

    @Test
    void testGetAllTransactions_ShouldReturnUserTransactions() {
        // Arrange
        List<Transaction> mockTransactions = List.of(
                testTransaction,
                new Transaction(2, "Transaction 2", new BigDecimal("200.00"), 
                        LocalDate.now(), Transaction.Type.EINKOMMEN, "desc", null, "testuser")
        );

        when(transactionRepo.findAllByOwnerOrderByDateDesc("testuser")).thenReturn(mockTransactions);

        // Act
        try (MockedStatic<SecurityUtils> mockedSecurity = mockStatic(SecurityUtils.class)) {
            mockedSecurity.when(SecurityUtils::getCurrentUsername).thenReturn("testuser");
            
            List<Transaction> result = transactionService.getAllTransactions();

            // Assert
            assertEquals(2, result.size());
            verify(transactionRepo, times(1)).findAllByOwnerOrderByDateDesc("testuser");
        }
    }

    @Test
    void testSaveTransaction_WithNullAmount_ShouldStillSave() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setTitle("No Amount");
        transaction.setAmount(null);

        when(transactionRepo.save(any(Transaction.class))).thenReturn(transaction);

        // Act
        try (MockedStatic<SecurityUtils> mockedSecurity = mockStatic(SecurityUtils.class)) {
            mockedSecurity.when(SecurityUtils::getCurrentUsername).thenReturn("user");
            
            Transaction result = transactionService.saveTransaction(transaction);

            // Assert
            assertNotNull(result);
            assertNull(result.getAmount());
        }
    }
}
