package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Category;
import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import com.WebTechProjekt.Expense_Tracker.util.SecurityUtils;
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

//    private final TransactionRepo transactionRepo;

    // In-Memory Liste für Beispieltransaktionen
    private static List<Transaction> transactions = new ArrayList<>();

//    static {
//        transactions.add(new Transaction(1L, "Einkauf im Supermarkt", new BigDecimal("75.50"),
//                LocalDate.now().minusDays(1), Transaction.Type.AUSGABEN, "Wöchentlicher Einkauf",
//                new Category(1L, "Lebensmittel", "Einkäufe für den täglichen Bedarf", "#FF9800",null),null));
//
//        transactions.add(new Transaction(2L, "Gehalt", new BigDecimal("2500.00"),
//                LocalDate.now().minusDays(5), Transaction.Type.EINKOMMEN, "Monatsgehalt",
//                new Category(2L, "Arbeit", "Einkommen aus Beschäftigung", "#4CAF50",null),null));
//
//        transactions.add(new Transaction(3L, "Benzin", new BigDecimal("40.00"),
//                LocalDate.now().minusDays(3), Transaction.Type.AUSGABEN, "Tankfüllung",
//                new Category(3L, "Transport", "Kosten für Mobilität", "#2196F3",null),null));
//
//        transactions.add(new Transaction(4L, "Restaurantbesuch", new BigDecimal("60.00"),
//                LocalDate.now().minusDays(2), Transaction.Type.AUSGABEN, "Dinner mit Freunden",
//                new Category(4L, "Freizeit", "Essen, Ausgehen und Unterhaltung", "#E91E63",null),null));
//
//        transactions.add(new Transaction(5L, "Büchereiausgabe", new BigDecimal("15.00"),
//                LocalDate.now().minusDays(4), Transaction.Type.AUSGABEN, "Kauf eines Buches",
//                new Category(5L, "Bildung", "Bücher und Lernmaterialien", "#9C27B0",null),null));
//
//        transactions.add(new Transaction(6L, "Nebeneinkommen", new BigDecimal("200.00"),
//                LocalDate.now().minusDays(6), Transaction.Type.EINKOMMEN, "Freelance Projekt",
//                new Category(6L, "Freelance", "Nebenverdienste und Projekte", "#8BC34A",null),null));
//
//        transactions.add(new Transaction(7L, "Fitnessstudio", new BigDecimal("35.00"),
//                LocalDate.now().minusDays(3), Transaction.Type.AUSGABEN, "Monatsbeitrag",
//                new Category(7L, "Gesundheit", "Sport und Fitness", "#03A9F4",null),null));
//
//        transactions.add(new Transaction(8L, "Geschenk", new BigDecimal("100.00"),
//                LocalDate.now().minusDays(7), Transaction.Type.AUSGABEN, "Geburtstagsgeschenk",
//                new Category(8L, "Geschenke", "Präsente und Anlässe", "#FFC107",null),null));
//    }


//    @Autowired
//    public TransactionService(TransactionRepo transactionRepo) {
//        this.transactionRepo = transactionRepo;
//    }

    public TransactionService(){

    }

    public Transaction getTransaction(Long id) {
        String owner = SecurityUtils.getCurrentUsername();
        return transactions.stream()
                .filter(tx -> Objects.equals(tx.getId(), id) && Objects.equals(tx.getOwner(), owner))
                .findFirst()
                .orElse(null);
    }

    public List<Transaction> getAllTransactions() {
        String owner = SecurityUtils.getCurrentUsername();
        return transactions.stream()
                .filter(tx -> Objects.equals(tx.getOwner(), owner))
                .collect(Collectors.toList());
    }

    public Transaction saveTransaction(Transaction transaction) {
        transaction.setOwner(SecurityUtils.getCurrentUsername());
        if(transaction.getId() == null){
            transaction.setId(generateUniqueId());
        }
        transactions.add(transaction);
        return transaction;
    }
    private Long generateUniqueId() {
        return transactions.stream()
                .map(Transaction::getId)
                .max(Long::compare) // Ermittelt die höchste vorhandene ID
                .orElse(0L) + 1;    // Nächste ID um 1 erhöhen
    }

    public Transaction updateTransaction(Long id, Transaction payload) {
        String owner = SecurityUtils.getCurrentUsername();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction current = transactions.get(i);
            if (Objects.equals(current.getId(), id)) {
                if (!Objects.equals(current.getOwner(), owner)) {
                    return null; // fremde Transaktion
                }
                Transaction merged = new Transaction(
                        id,
                        payload.getTitle(),
                        payload.getAmount(),
                        payload.getDate(),
                        payload.getType(),
                        payload.getDescription(),
                        payload.getCategory(),
                        owner
                );
                transactions.set(i, merged);
                return merged;
            }
        }
        return null;
    }


    public boolean deleteTransaction(Long id) {
        String owner = SecurityUtils.getCurrentUsername();
        return transactions.removeIf(tx -> Objects.equals(tx.getId(), id) && Objects.equals(tx.getOwner(), owner));
    }

    public BigDecimal getTotal() {
        String owner = SecurityUtils.getCurrentUsername();
        return transactions.stream()
                .filter(tx -> Objects.equals(tx.getOwner(),owner))
                .map(tx -> tx.getType() == Transaction.Type.EINKOMMEN
                    ? tx.getAmount()
                    : tx.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
