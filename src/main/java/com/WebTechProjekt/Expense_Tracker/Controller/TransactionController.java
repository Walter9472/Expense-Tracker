package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/et")
public class TransactionController {


    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable int id){
        Transaction tx = transactionService.getTransaction(id);

        if(tx == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return tx;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(){
        return transactionService.getAllTransactions();
    }


    @GetMapping("/transactions/Balance")
    public BigDecimal getBalance(){
        return transactionService.getTotal();
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedTransaction);
    }

    @PutMapping("transaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable int id,@RequestBody Transaction transaction){
        Transaction updatedTransaction = transactionService.updateTransaction(id,transaction);
        if(updatedTransaction == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return  ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("transaction/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int id){
        if (!transactionService.deleteTransaction(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.noContent().build();
    }









}
