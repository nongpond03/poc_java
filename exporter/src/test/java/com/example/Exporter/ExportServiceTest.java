package com.example.Exporter;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.Exporter.ExcelHelper.azzertEquals;
import static com.example.Exporter.ExcelHelper.readExcel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExportServiceTest {

    @InjectMocks
    ExportService service;

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    void Convert_1_Entity_To_Excel_Correctly() throws IOException {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction first = Transaction.builder()
                .id(22L)
                .partiId("099")
                .agentCode("099")
                .effectiveDate(LocalDate.now())
                .fcdReferenceCode("116p19t48a5vjw42")
                .referenceCode("20230721114728688")
                .merchantCode("TESTCOM")
                .companyCode("22222")
                .customerNo("121212")
                .transactionType("C")
                .isFcd("Y")
                .transferAmount(BigDecimal.valueOf(2000.00))
                .currency("THB")
                .sendingBankCode("006")
                .sendingBankAccountNo("333")
                .sendingBankAccountName("James Bond")
                .sendingBankAccountTaxId("7777")
                .receivingBankCode("006")
                .receivingBankAccountNo("333")
                .receivingBankAccountName("James Bond")
                .receivingBankAccountTaxId("7777")
                .botPurposeCode("999999")
                .botPurposeName("interbank transfer")
                .botPurposeValue("999999")
                .statusCode("56")
                .successAmount(BigDecimal.valueOf(1000.00))
                .createdDate(LocalDateTime.of(2023, 7, 22, 7, 0, 0))
                .updatedDate(LocalDateTime.of(2023, 7, 22, 7, 1, 0))
                .build();
        transactionList.add(first);
        Object[][] xData = {
                {"id", "partiId", "agentCode", "effectiveDate", "fcdReferenceCode", "referenceCode", "merchantCode", "companyCode", "customerNo", "transactionType", "isFcd", "transferAmount", "currency", "sendingBankCode", "sendingBankAccountNo", "sendingBankAccountName", "sendingBankAccountTaxId", "receivingBankCode", "receivingBankAccountNo", "receivingBankAccountName", "receivingBankAccountTaxId", "botPurposeCode", "botPurposeName", "botPurposeValue", "statusCode", "successAmount", "createdDate", "updatedDate"},
                {"22", "099", "099", "2024-03-15", "116p19t48a5vjw42", "20230721114728688", "TESTCOM", "22222", "121212", "C", "Y", Double.valueOf(2000.00), "THB", "006", "333", "James Bond", "7777", "006", "333", "James Bond", "7777", "999999", "interbank transfer", "999999", "56", Double.valueOf(1000.00), "2023-07-22T07:00:00", "2023-07-22T07:01:00"},
        };

        ByteArrayOutputStream resp = service.convertEntitiesToExcel("test", transactionList);
        Sheet sheet = readExcel(resp);

        assertEquals(xData.length, sheet.getPhysicalNumberOfRows(), "Number of rows doesn't match");
        azzertEquals(xData, sheet);
    }

    @Test
    void Convert_2_Entities_To_Excel_Correctly() throws IOException {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction first = Transaction.builder()
                .id(22L)
                .partiId("099")
                .agentCode("099")
                .effectiveDate(LocalDate.now())
                .fcdReferenceCode("116p19t48a5vjw42")
                .referenceCode("20230721114728688")
                .merchantCode("TESTCOM")
                .companyCode("22222")
                .customerNo("121212")
                .transactionType("C")
                .isFcd("Y")
                .transferAmount(BigDecimal.valueOf(2000.00))
                .currency("THB")
                .sendingBankCode("006")
                .sendingBankAccountNo("333")
                .sendingBankAccountName("James Bond")
                .sendingBankAccountTaxId("7777")
                .receivingBankCode("006")
                .receivingBankAccountNo("333")
                .receivingBankAccountName("James Bond")
                .receivingBankAccountTaxId("7777")
                .botPurposeCode("999999")
                .botPurposeName("interbank transfer")
                .botPurposeValue("999999")
                .statusCode("56")
                .successAmount(BigDecimal.valueOf(1000.00))
                .createdDate(LocalDateTime.of(2023, 7, 22, 7, 0, 0))
                .updatedDate(LocalDateTime.of(2023, 7, 22, 7, 1, 0))
                .build();
        Transaction second = Transaction.builder()
                .id(11L)
                .partiId("099")
                .agentCode("099")
                .effectiveDate(LocalDate.now())
                .fcdReferenceCode("116p19t48a5vjw42")
                .referenceCode("20230721114728688")
                .merchantCode("TESTCOM")
                .companyCode("22222")
                .customerNo("121212")
                .transactionType("C")
                .isFcd("Y")
                .transferAmount(BigDecimal.valueOf(1000.00))
                .currency("THB")
                .sendingBankCode("006")
                .sendingBankAccountNo("333")
                .sendingBankAccountName("James Bond")
                .sendingBankAccountTaxId("7777")
                .receivingBankCode("006")
                .receivingBankAccountNo("333")
                .receivingBankAccountName("James Bond")
                .receivingBankAccountTaxId("7777")
                .botPurposeCode("999999")
                .botPurposeName("interbank transfer")
                .botPurposeValue("999999")
                .statusCode("56")
                .successAmount(BigDecimal.valueOf(1000.01))
                .createdDate(LocalDateTime.of(2023, 7, 22, 7, 0, 12))
                .updatedDate(LocalDateTime.of(2023, 7, 22, 7, 1, 12))
                .build();
        transactionList.add(first);
        transactionList.add(second);
        Object[][] xData = {
                {"id", "partiId", "agentCode", "effectiveDate", "fcdReferenceCode", "referenceCode", "merchantCode", "companyCode", "customerNo", "transactionType", "isFcd", "transferAmount", "currency", "sendingBankCode", "sendingBankAccountNo", "sendingBankAccountName", "sendingBankAccountTaxId", "receivingBankCode", "receivingBankAccountNo", "receivingBankAccountName", "receivingBankAccountTaxId", "botPurposeCode", "botPurposeName", "botPurposeValue", "statusCode", "successAmount", "createdDate", "updatedDate"},
                {"22", "099", "099", "2024-03-15", "116p19t48a5vjw42", "20230721114728688", "TESTCOM", "22222", "121212", "C", "Y", Double.valueOf(2000.00), "THB", "006", "333", "James Bond", "7777", "006", "333", "James Bond", "7777", "999999", "interbank transfer", "999999", "56", Double.valueOf(1000.00), "2023-07-22T07:00:00", "2023-07-22T07:01:00"},
                {"11", "099", "099", "2024-03-15", "116p19t48a5vjw42", "20230721114728688", "TESTCOM", "22222", "121212", "C", "Y", Double.valueOf(1000.00), "THB", "006", "333", "James Bond", "7777", "006", "333", "James Bond", "7777", "999999", "interbank transfer", "999999", "56", Double.valueOf(1000.01), "2023-07-22T07:00:12", "2023-07-22T07:01:12"},
        };

        ByteArrayOutputStream resp = service.convertEntitiesToExcel("test", transactionList);
        Sheet sheet = readExcel(resp);

        assertEquals(xData.length, sheet.getPhysicalNumberOfRows(), "Number of rows doesn't match");
        azzertEquals(xData, sheet);
    }

    @Test
    void Convert_Empty_Entity_To_Excel_Correctly() throws IOException {
        List<Transaction> transactionList = new ArrayList<>();
        Object[][] xData = {{}};

        ByteArrayOutputStream resp = service.convertEntitiesToExcel("test", transactionList);
        Sheet sheet = readExcel(resp);

        assertEquals(xData.length, sheet.getPhysicalNumberOfRows(), "Number of rows doesn't match");
        azzertEquals(xData, sheet);
    }
}