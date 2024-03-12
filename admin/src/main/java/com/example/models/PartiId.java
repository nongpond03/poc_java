package com.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartiId {
    private String partiId;
    private String abbr;
    private String nameTh;
    private String nameEn;
    private String email;
    private boolean active;
    private boolean broker;
    private String ip;
    private String activeQR;
    private String activeDDR;
    private String activePromptPay;
}
