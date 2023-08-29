package ru.kopnin.bankservice.models.postgres;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
   // @Pattern(regexp = "d{10}")
    @Column(name = "account_from")
    private String accountFrom;
    @NotEmpty
  //  @Pattern(regexp = "d{10}")
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
    @Column(name = "limit_exceeded")
    private boolean limitExceeded;
    @ManyToOne
    @JoinColumn(name = "transaction_client", nullable = true)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "transaction_limit", nullable = true)
    private Limit limit;
}
