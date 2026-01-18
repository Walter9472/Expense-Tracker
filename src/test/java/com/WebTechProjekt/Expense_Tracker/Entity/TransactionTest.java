package com.WebTechProjekt.Expense_Tracker.Entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testUserCreationWithAllArgsConstructor() {
        // Arrange & Act
        Transaction tx = new Transaction(
                1,
                "Wocheneinkauf Rewe", 
                BigDecimal.valueOf(85.47),
                LocalDate.of(2025,12,18),
                Transaction.Type.AUSGABEN, 
                "Wöchentlicher Großeinkauf",
                new Category(1,"Lebensmittel", "Einkäufe im Supermarkt, Bäcker, Metzger etc.", "#4CAF50", "Walter"),
                "Walter");

        // Assert
        assertEquals(1, tx.getId());
        assertEquals("Wocheneinkauf Rewe", tx.getTitle());
        assertEquals(BigDecimal.valueOf(85.47), tx.getAmount());
        assertEquals(LocalDate.of(2025,12,18), tx.getDate());
        assertEquals(Transaction.Type.AUSGABEN, tx.getType());
        assertEquals("Wöchentlicher Großeinkauf", tx.getDescription());
        assertEquals(new Category(1,"Lebensmittel", "Einkäufe im Supermarkt, Bäcker, Metzger etc.", "#4CAF50", "Walter"), tx.getCategory());
        assertEquals("Walter",tx.getOwner());
    }

    @Test
    void testUserCreationWithNoArgsConstructor() {
        // Act
        Transaction tx = new Transaction();

        // Assert

        System.out.println(tx);
        System.out.println(tx.getId());
        System.out.println(tx.getTitle());
        System.out.println(tx.getAmount());
        System.out.println(tx.getDate());
        System.out.println(tx.getType());
        System.out.println(tx.getDescription());
        System.out.println(tx.getCategory());
        System.out.println(tx.getOwner());

        assertNotNull(tx);
        assertEquals(0, tx.getId());
        assertNull(tx.getTitle());
        assertNull(tx.getAmount());
        assertNull(tx.getDate());
        assertNull(tx.getType());
        assertNull(tx.getDescription());
        assertNull(tx.getCategory());
        assertNull(tx.getOwner());
    }

    @Test
    void testUserSettersAndGetters() {
        // Arrange
        Transaction tx = new Transaction();

        // Act
        tx.setId(42);
        tx.setTitle("Einkauf");
        tx.setAmount(BigDecimal.valueOf(30.0));
        tx.setDate(LocalDate.of(2025,12,1));
        tx.setType(Transaction.Type.AUSGABEN);
        tx.setDescription("Großeinkauf bei Edeka");
        tx.setCategory(new Category(1,"Lebensmittel", "Einkäufe im Supermarkt, Bäcker, Metzger etc.", "#4CAF50", "Walter"));
        tx.setOwner("Walter");

        // Assert
        assertEquals(42, tx.getId());
        assertEquals("Einkauf", tx.getTitle());
        assertEquals(BigDecimal.valueOf(30.0), tx.getAmount());
        assertEquals(LocalDate.of(2025,12,1), tx.getDate());
        assertEquals(Transaction.Type.AUSGABEN, tx.getType());
        assertEquals("Großeinkauf bei Edeka", tx.getDescription());
        assertEquals(new Category(1,"Lebensmittel", "Einkäufe im Supermarkt, Bäcker, Metzger etc.", "#4CAF50", "Walter"), tx.getCategory());
        assertEquals("Walter",tx.getOwner());
    }
}