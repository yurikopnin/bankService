package kopnin.ru.bankservice.DTO;

import lombok.Data;


import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LimitDTO {
    @NotEmpty(message = " Field may not be empty")
    private String bankAccountNumber;
    private BigDecimal limitSum;
    private String limitCurrencyShortname;
    private String limitExpenseCategory;

}
