package kopnin.ru.bankservice.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="conversions")
public class Conversions {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name="rate")
    private BigDecimal rate;
    @Column(name="rateOnPreviousClose")
    private BigDecimal rateOnPreviousClose;
    @Column(name="madeAt")
    private LocalDateTime madeAt;
    @Column(name="symbol")
    private String symbol;

    public Conversions() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRateOnPreviousClose() {
        return rateOnPreviousClose;
    }

    public void setRateOnPreviousClose(BigDecimal rateOnPreviousClose) {
        this.rateOnPreviousClose = rateOnPreviousClose;
    }

    public LocalDateTime getMadeAt() {
        return madeAt;
    }

    public void setMadeAt(LocalDateTime madeAt) {
        this.madeAt = madeAt;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
