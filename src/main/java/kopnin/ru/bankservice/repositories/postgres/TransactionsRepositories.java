package kopnin.ru.bankservice.repositories.postgres;

import kopnin.ru.bankservice.models.postgres.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepositories extends JpaRepository<Transaction, Long> {
}
