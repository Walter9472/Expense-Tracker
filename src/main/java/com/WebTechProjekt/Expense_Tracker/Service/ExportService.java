package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExportService {

    @Autowired
    private TransactionService transactionService;

    private static final String[] HEADERS = {
            "ID", "Titel", "Betrag", "Datum", "Typ", "Kategorie", "Beschreibung"
    };

    public String generateTransactionCsv() throws IOException {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return writeCsv(transactions);
    }

    public String generateTransactionCsvByDateRange(LocalDate start, LocalDate end) throws IOException {
        List<Transaction> transactions = transactionService.getAllTransactions()
                .stream()
                .filter(tx -> !tx.getDate().isBefore(start) && !tx.getDate().isAfter(end))
                .toList();
        return writeCsv(transactions);
    }

    private String writeCsv(List<Transaction> transactions) throws IOException {
        StringWriter writer = new StringWriter();

        // EXCEL-Format mit Semikolon für deutsche Excel-Versionen
        CSVFormat format = CSVFormat.EXCEL.builder()
                .setDelimiter(';')  // Semikolon für deutsches Excel
                .setHeader(HEADERS)
                .build();

        try (CSVPrinter printer = new CSVPrinter(writer, format)) {
        for(Transaction tx : transactions){
            printer.printRecord(
                    tx.getId(),
                    tx.getTitle(),
                    tx.getAmount(),
                    tx.getDate(),
                    tx.getType(),
                    tx.getCategory() != null ? tx.getCategory().getName() : "",
                    tx.getDescription() != null ? tx.getDescription() : "");
        }
        }
        return writer.toString();
    }
}
