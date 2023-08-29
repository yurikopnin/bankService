package ru.kopnin.bankservice.DTO;

import lombok.Data;
import java.math.BigDecimal;
@Data
public class TransactionDTO {
    private String accountFrom;
    private String accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    private String expenseCategory;
    private String TransactionDateTime;
}
