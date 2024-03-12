package com.example.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    private String bankCode;
    private String abbr;
    private String nameTh;
    private String nameEn;
    private String qrEmailReport;
    private boolean active;
    private String activeQR;
    private String activeDDR;
    private String activePromptPay;
}
