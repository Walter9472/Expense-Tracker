package com.WebTechProjekt.Expense_Tracker.Controller;

import com.WebTechProjekt.Expense_Tracker.Service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller("/et/export")
public class ExportController {

    private final ExportService exportService;

    @Autowired
    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/csv")
    public ResponseEntity<byte[]>exportCsv(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
            ) throws IOException {

        String csvContent;

        // Mit oder ohne Datumsfilter
        if (startDate != null && endDate != null) {
            csvContent = exportService.generateTransactionCsvByDateRange(startDate, endDate);
        }else  {
            csvContent = exportService.generateTransactionCsv();
        }

        // Dateiname mit aktuellem Datum
        String filename = "transactions_" + LocalDate.now().format(DateTimeFormatter.ISO_DATE) + ".csv";

        // Response mit Download-Header
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.parseMediaType("text/csv; charset=UTF-8"))
                .body(csvContent.getBytes("UTF-8"));

    }


}
