package com.example.Exporter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class ExportController {
    private final TransactionRepository transactionRepository;
    private final TransactionCreditRepository transactionCreditRepository;
    private final ExportService exportService;

    public ExportController(TransactionRepository transactionRepository, TransactionCreditRepository transactionCreditRepository, ExportService exportService) {
        this.transactionRepository = transactionRepository;
        this.transactionCreditRepository = transactionCreditRepository;
        this.exportService = exportService;
    }

    @GetMapping("/export")
    public ResponseEntity<Void> exportToExcel(HttpServletResponse response) throws IOException {
        List<Transaction> transactions = transactionRepository.findByEffectiveDate(LocalDate.now());
        List<TransactionCredit> transactionCredits = transactionCreditRepository.findByEffectiveDate(LocalDate.now());
        ByteArrayOutputStream outputStream = exportService.convertEntitiesToExcel("test", transactions, transactionCredits);

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=entities.xlsx";
        response.setHeader(headerKey, headerValue);

        response.getOutputStream().write(outputStream.toByteArray());
        response.getOutputStream().close();

        return ResponseEntity.ok().build();
    }
}
