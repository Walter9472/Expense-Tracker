package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @GetMapping("/transaction")
    public List<Transaction> getTransactions(){

        return List.of(new Transaction(), new Transaction(),new Transaction());

    }

}
