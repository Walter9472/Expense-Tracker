package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;
    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public Transaction getTransaction(Long id) {
        return null;

    }

    public List<Transaction> getAllTransactions() {
        return null;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return null;
    }

    public Transaction updateTransaction() {
        return null;
    }


    public Transaction deleteTransaction() {
        return null;
    }


}
