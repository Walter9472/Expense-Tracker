package com.WebTechProjekt.Expense_Tracker.Repository;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByOwnerOrderByDateDesc(String owner);
}
