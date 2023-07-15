package kopnin.ru.bankservice.models.postgres;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
