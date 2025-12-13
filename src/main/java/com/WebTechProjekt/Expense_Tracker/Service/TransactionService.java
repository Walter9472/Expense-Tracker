package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Repository.TransactionRepo;
import com.WebTechProjekt.Expense_Tracker.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;


    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public Transaction getTransaction(int id) {
        String owner = SecurityUtils.getCurrentUsername();
        Transaction transaction = transactionRepo.findById(id).orElse(null);


        if(Objects.equals(transaction.getId(),id) && Objects.equals(transaction.getOwner(),owner)) {
            return transaction;
        }else
            return null;

    }


    public List<Transaction> getAllTransactions() {
        String owner = SecurityUtils.getCurrentUsername();
        return transactionRepo.findAllByOwnerOrderByDateDesc(owner);
    }

    public Transaction saveTransaction(Transaction transaction) {
        transaction.setOwner(SecurityUtils.getCurrentUsername());
        // ID wird automatisch durch @GeneratedValue (IDENTITY) und Datenbank generiert
        return transactionRepo.save(transaction);
    }


    public Transaction updateTransaction(int id, Transaction payload) {
       String owner = SecurityUtils.getCurrentUsername();

        if(Objects.equals(payload.getId(),id) && Objects.equals(payload.getOwner(),owner)) {
            return transactionRepo.save(payload);
        }else
            return null;
    }


    public boolean deleteTransaction(int id) {
        String owner = SecurityUtils.getCurrentUsername();
        Transaction transaction = transactionRepo.findById(id).orElse(null);
        if(Objects.equals(transaction.getId(),id) && Objects.equals(transaction.getOwner(),owner)) {
            transactionRepo.delete(transaction);
            return true;
        } else
            return false;
    }

    public BigDecimal getTotal() {
        String owner = SecurityUtils.getCurrentUsername();
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream()
                .filter(tx -> Objects.equals(tx.getOwner(),owner))
                .map(tx -> tx.getType() == Transaction.Type.EINKOMMEN
                        ? tx.getAmount()
                        : tx.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }


}
