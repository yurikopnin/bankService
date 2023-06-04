package kopnin.ru.bankservice.repositories.cassandra;

import kopnin.ru.bankservice.models.cassandra.Conversions;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ConversionsRepositories extends CassandraRepository<Conversions, UUID> {
}
