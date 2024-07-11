package com.example.Exporter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionCredit {
    @Id
    private Long id;
    private String partiId;
    private String agentCode;
    private LocalDate effectiveDate;
    private String fcdReferenceCode;
    private String referenceCode;
    private String merchantCode;
    private String companyCode;
    private String customerNo;
    private String transactionType;
    private String isFcd;
    private BigDecimal transferAmount;
    private String currency;
    private String sendingBankCode;
    private String sendingBankAccountNo;
    private String sendingBankAccountName;
    private String sendingBankAccountTaxId;
    private String receivingBankCode;
    private String receivingBankAccountNo;
    private String receivingBankAccountName;
    private String receivingBankAccountTaxId;
    private String botPurposeCode;
    private String botPurposeName;
    private String botPurposeValue;
    private String statusCode;
    private BigDecimal successAmount;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
