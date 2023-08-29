package ru.kopnin.bankservice.repositories.postgres;

import ru.kopnin.bankservice.models.postgres.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepositories extends JpaRepository<Transaction, Long> {
}
