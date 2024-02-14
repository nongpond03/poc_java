package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "broker")
public class Broker {
    public static String BROKER = "broker";
    public static String NON_BROKER = "non-broker";

    @Id
    @Column(name = "PARTI_ID", nullable = false, length = 5)
    private String partiId;

    @Column(name = "ABBR", length = 20)
    private String abbr;

    @Column(name = "NAME_TH")
    private String nameTh;

    @Column(name = "NAME_EN")
    private String nameEn;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "IS_BROKER")
    private boolean broker;

    @Column(name = "IP")
    private String ip;

    @Transient
    private Boolean activeQR;

    @Transient
    private Boolean activeDDR;

    @Transient
    private Boolean activePromptPay;
}
