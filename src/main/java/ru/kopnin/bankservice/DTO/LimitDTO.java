package ru.kopnin.bankservice.DTO;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
@Data
public class LimitDTO {
    @NotEmpty(message = " Field may not be empty")
    private String bankAccountNumber;
    private BigDecimal limitSum;
    private String limitCurrencyShortname;
    private String limitExpenseCategory;
}
