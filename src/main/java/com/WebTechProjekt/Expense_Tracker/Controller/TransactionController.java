package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {


    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction")
    public List<Transaction> getTransactions(){

        return List.of(new Transaction(), new Transaction(),new Transaction());

    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable Long id){
        return transactionService.getTransaction(id);
    }



}
