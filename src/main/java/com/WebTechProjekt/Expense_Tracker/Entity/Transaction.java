package com.WebTechProjekt.Expense_Tracker.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    private int id = 0;
    private String title;             // z. B. "Einkauf im Supermarkt"
    private BigDecimal amount;
    private LocalDate date;
    private Type type;              // "EXPENSE" oder "INCOME"
    private String description;       // optionaler Text
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;        // Beziehung zu Category
    private String owner;
    //private User user;                // Beziehung zu User

    public enum Type{
        EINKOMMEN,AUSGABEN
    }
}

