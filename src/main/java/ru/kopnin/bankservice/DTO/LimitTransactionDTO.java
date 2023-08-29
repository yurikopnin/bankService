package ru.kopnin.bankservice.DTO;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class LimitTransactionDTO {
    //Переменные сущности лимит
    private String limitDateTime;
    private BigDecimal limitSum;
    private BigDecimal remainingMonthLimit;
    //Переменные сущности Транзакция
    private String transactionDateTime;
    private BigDecimal transactionSum;
    private boolean limitExceeded;

}
