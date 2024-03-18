package com.example.Exporter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@RestController
public class ExportController {
    private final TransactionRepository transactionRepository;
    private final ExportService exportService;

    public ExportController(TransactionRepository transactionRepository, ExportService exportService) {
        this.transactionRepository = transactionRepository;
        this.exportService = exportService;
    }

    @GetMapping("/export")
    public ResponseEntity<Void> exportToExcel(HttpServletResponse response) throws IOException {
        ByteArrayOutputStream outputStream = exportService.convertEntitiesToExcel("test", transactionRepository.findByEffectiveDate(LocalDate.now()), transactionRepository.findByEffectiveDate(LocalDate.now()));

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=entities.xlsx";
        response.setHeader(headerKey, headerValue);

        response.getOutputStream().write(outputStream.toByteArray());
        response.getOutputStream().close();

        return ResponseEntity.ok().build();
    }
}
