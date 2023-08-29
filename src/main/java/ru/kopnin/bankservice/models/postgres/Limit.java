package ru.kopnin.bankservice.models.postgres;
import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "limits")
public class Limit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PositiveOrZero
    @Column(name = "limit_sum")
    private BigDecimal limitSum;
    @Column(name = "remaining_month_limit")
    private BigDecimal remainingMonthLimit;
    @Column(name = "limit_datetime")
    private LocalDateTime limitDateTime;
    @Column(name = "limit_currency_shortname")
    private String limitCurrencyShortname;
    @Column(name = "limit_expense_category")
    private String limitExpenseCategory;
    @ManyToOne
    @JoinColumn(name = "limit_сlient", nullable = true)
    private Client client;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_limit ")
//    private List<Transaction> limitTransactions;
}
