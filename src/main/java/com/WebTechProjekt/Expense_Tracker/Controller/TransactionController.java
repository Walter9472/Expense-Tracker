package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {


    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable Long id){
        return transactionService.getTransaction(id);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(){
        return transactionService.getAllTransactions();
    }
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransation(@RequestBody Transaction transaction){
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedTransaction);
    }

    @PutMapping("transaction{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id,@RequestBody Transaction transaction){
        Transaction updatedTransaction = transactionService.updateTransaction(id,transaction);

        if(updatedTransaction != null){
            return ResponseEntity.ok(updatedTransaction);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("transaction{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }








}
