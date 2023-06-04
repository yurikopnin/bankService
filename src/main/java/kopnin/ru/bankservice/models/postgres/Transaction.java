package kopnin.ru.bankservice.models.postgres;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Pattern(regexp = "d{10}")
    @Column(name = "account_from")
    private String accountFrom;
    @NotEmpty
    @Pattern(regexp = "d{10}")
    @Column(name = "account_to")
    private String accountTo;
    @PositiveOrZero
    @Column(name = "sum")
    private BigDecimal sum;
    @Column(name = "currency_shortname")
    private String currencyShortname;
    @Column(name = "expense_category")
    private String expenseCategory;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "datetime")
    private LocalDateTime datetime;
    @Column(name = "transaction_client")
    private Long transactionClient;
    @Column(name = "transaction_limit")
    private Long transactionLimit;
    @Column(name = "limit_exceeded")
    private boolean limitExceeded;


}
