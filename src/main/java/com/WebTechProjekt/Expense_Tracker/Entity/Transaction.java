package com.WebTechProjekt.Expense_Tracker.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
//Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    //@Id
    private Long id;
    private String title;             // z. B. "Einkauf im Supermarkt"
    private BigDecimal amount;
    private LocalDate date;
    private Type type;              // "EXPENSE" oder "INCOME"
    private String description;       // optionaler Text
    private Category category;        // Beziehung zu Category
    //private User user;                // Beziehung zu User

    public enum Type{
        EINKOMMEN,AUSGABEN
    }
}

