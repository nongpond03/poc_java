package com.example.demo.security;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AESRequest {
    @NotBlank
    private String effectiveDate;
    @NotBlank
    private String partiId;
    @NotBlank
    private String referenceCode;
}
