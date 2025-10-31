package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

//    private final TransactionRepo transactionRepo;

    // In-Memory Liste für Beispieltransaktionen
    private static List<Transaction> transactions = new ArrayList<>();

    static {
        transactions.add(new Transaction(1L, "Einkauf im Supermarkt", new BigDecimal("75.50"),
                LocalDate.now().minusDays(1), "EXPENSE", "Wöchentlicher Einkauf",new Category()));
        transactions.add(new Transaction(2L, "Gehalt", new BigDecimal("2500.00"),
                LocalDate.now().minusDays(5), "INCOME", "Monatsgehalt",new Category()));
        transactions.add(new Transaction(3L, "Benzin", new BigDecimal("40.00"),
                LocalDate.now().minusDays(3), "EXPENSE", "Tankfüllung",new Category()));
    }

//    @Autowired
//    public TransactionService(TransactionRepo transactionRepo) {
//        this.transactionRepo = transactionRepo;
//    }

    public TransactionService(){

    }

    public Transaction getTransaction(Long id) {
        Optional<Transaction> optional = transactions.stream().filter(trans -> trans.getId().equals(id)).findFirst();
        return optional.orElse(null);

    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public Transaction saveTransaction(Transaction transaction) {
        transactions.add(transaction);
        return transaction;
    }

    public Transaction updateTransaction(Long id, Transaction transaction) {

        for(int i = 0; i < transactions.size();i++){
            Transaction current = transactions.get(i);
            if(transaction.getId().equals(current.getId())){
                transaction.setId(id);
                transactions.set(i,transaction);
                return transaction;
            }
        }
        return null;
    }


    public void deleteTransaction(Long id) {
         transactions.removeIf(trans -> trans.getId().equals(id));
    }


}
