package ru.kopnin.bankservice.models.cassandra;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.math.BigDecimal;
@Data
@Table("conversions")
public class Conversion {
    @PrimaryKey
    private ConversionKey key;
    @Column("rate")
    private BigDecimal rate;
    @Column("rate_on_previous_close")
    private BigDecimal rateOnPreviousClose;
}
