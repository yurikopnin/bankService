package ru.kopnin.bankservice.models.cassandra;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Data
@PrimaryKeyClass
public class ConversionKey implements Serializable {

    @PrimaryKeyColumn(name = "symbol", type = PARTITIONED)
    private String symbol;
    @PrimaryKeyColumn(name = "made_at", ordinal = 0)
    private LocalDateTime madeAt;
}
