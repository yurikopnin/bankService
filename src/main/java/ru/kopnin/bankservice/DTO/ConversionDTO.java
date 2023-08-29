package ru.kopnin.bankservice.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionDTO {
    private String symbol;
    private BigDecimal rate;
    private long timestamp;
}
