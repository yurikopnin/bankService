package ru.kopnin.bankservice.repositories.cassandra;

import ru.kopnin.bankservice.models.cassandra.Conversion;
import ru.kopnin.bankservice.models.cassandra.ConversionKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionsRepositories extends CassandraRepository<Conversion, ConversionKey> {
}
