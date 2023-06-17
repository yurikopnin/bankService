package kopnin.ru.bankservice.models.cassandra;


import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


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
