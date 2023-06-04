package kopnin.ru.bankservice.models.postgres;



import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name="Limits")
public class Limits {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PositiveOrZero
    @Column(name="limitSum")
    private BigDecimal limitSum;
    @Column(name="remainingMonthLimit")
    private BigDecimal remainingMonthLimit;
    @Column(name="limitDateTime")
    private LocalDateTime limitDateTime;
    @Column(name="limitCurrencyShortname")
    private String limitCurrencyShortname;
    @Column(name="limitClient")
    private Long limitClient;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="transactionLimit ")
    private List<Transactions> limitTransactions;

    public Limits() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLimitSum() {
        return limitSum;
    }

    public void setLimitSum(BigDecimal limitSum) {
        this.limitSum = limitSum;
    }

    public BigDecimal getRemainingMonthLimit() {
        return remainingMonthLimit;
    }

    public void setRemainingMonthLimit(BigDecimal remainingMonthLimit) {
        this.remainingMonthLimit = remainingMonthLimit;
    }

    public LocalDateTime getLimitDateTime() {
        return limitDateTime;
    }

    public void setLimitDateTime(LocalDateTime limitDateTime) {
        this.limitDateTime = limitDateTime;
    }

    public String getLimitCurrencyShortname() {
        return limitCurrencyShortname;
    }

    public void setLimitCurrencyShortname(String limitCurrencyShortname) {
        this.limitCurrencyShortname = limitCurrencyShortname;
    }

    public Long getLimitClient() {
        return limitClient;
    }

    public void setLimitClient(Long limitClient) {
        this.limitClient = limitClient;
    }

    public List<Transactions> getLimitTransactions() {
        return limitTransactions;
    }

    public void setLimitTransactions(List<Transactions> limitTransactions) {
        this.limitTransactions = limitTransactions;
    }
}
