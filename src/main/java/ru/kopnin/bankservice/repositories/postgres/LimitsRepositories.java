package ru.kopnin.bankservice.repositories.postgres;

import ru.kopnin.bankservice.models.postgres.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitsRepositories extends JpaRepository<Limit,Long> {
}
