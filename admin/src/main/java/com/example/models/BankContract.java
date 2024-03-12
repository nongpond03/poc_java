package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankContract{
    private String id;
    private String partiId;
    private String bankCode;
    private String startDate;
    private String endDate;
    private String enable;
}
