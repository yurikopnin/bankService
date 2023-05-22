package kopnin.ru.bankservice.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Pattern(regexp = "d{10}")
    @Column(name="accountFrom")
    private String accountFrom;
    @NotEmpty
    @Pattern(regexp = "d{10}")
    @Column(name="accountTo")
    private String accountTo;
    @PositiveOrZero
    @Column(name="sum")
    private BigDecimal sum;
    @Column(name="currencyShortname")
    private String currencyShortname;
    @Column(name="expenseCategory")
    private  String expenseCategory;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="datetime")
    private LocalDateTime datetime;
    private Long transactionClient;
    private Long transactionLimit;
    @Column(name="limitExceeded")
    private boolean limitExceeded;

    public Transactions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getCurrencyShortname() {
        return currencyShortname;
    }

    public void setCurrencyShortname(String currencyShortname) {
        this.currencyShortname = currencyShortname;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Long getTransactionClient() {
        return transactionClient;
    }

    public void setTransactionClient(Long transactionClient) {
        this.transactionClient = transactionClient;
    }

    public Long getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(Long transactionLimit) {
        this.transactionLimit = transactionLimit;
    }

    public boolean isLimitExceeded() {
        return limitExceeded;
    }

    public void setLimitExceeded(boolean limitExceeded) {
        this.limitExceeded = limitExceeded;
    }
}
