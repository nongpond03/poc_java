package com.example.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent {
    private String agentCode;
    private String abbr;
    private String nameTh;
    private String nameEn;
    private boolean active;
    private String activeQR;
    private String activeDRR;
    private String activePromptPay;
    private String ip;
}
