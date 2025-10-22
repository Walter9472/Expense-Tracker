package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Repository.TransactionRepo;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    private final TransactionRepo transactionRepo;

    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public Transaction getTransaction(Long id) {
        return null;

    }
}
