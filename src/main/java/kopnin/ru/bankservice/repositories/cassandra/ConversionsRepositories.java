package kopnin.ru.bankservice.repositories.cassandra;

import kopnin.ru.bankservice.models.cassandra.Conversion;
import kopnin.ru.bankservice.models.cassandra.ConversionKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;
@Repository
public interface ConversionsRepositories extends CassandraRepository<Conversion, ConversionKey> {

   
}
