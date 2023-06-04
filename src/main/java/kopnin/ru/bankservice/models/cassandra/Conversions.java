package kopnin.ru.bankservice.models.cassandra;



import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("conversions")
public class Conversions {
    @PrimaryKey
    private UUID id;
    @Column("rate")
    private BigDecimal rate;
    @Column("rateOnPreviousClose")
    private BigDecimal rateOnPreviousClose;
    @Column("madeAt")
    private LocalDateTime madeAt;
    @Column("symbol")
    private String symbol;

    public Conversions() {
    }

    public Conversions(UUID id, BigDecimal rate, BigDecimal rateOnPreviousClose, LocalDateTime madeAt, String symbol) {
        this.id = id;
        this.rate = rate;
        this.rateOnPreviousClose = rateOnPreviousClose;
        this.madeAt = madeAt;
        this.symbol = symbol;
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

    @Override
    public String toString() {
        return "Conversions{" +
                "id=" + id +
                ", rate=" + rate +
                ", rateOnPreviousClose=" + rateOnPreviousClose +
                ", madeAt=" + madeAt +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
