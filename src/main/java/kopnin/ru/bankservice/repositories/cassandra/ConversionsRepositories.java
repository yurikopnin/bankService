package kopnin.ru.bankservice.repositories.cassandra;

import kopnin.ru.bankservice.models.cassandra.Conversion;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
//@Repository
public interface ConversionsRepositories extends CassandraRepository<Conversion, UUID> {
}
