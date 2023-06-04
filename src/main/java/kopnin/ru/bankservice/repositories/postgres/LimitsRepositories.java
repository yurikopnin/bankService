package kopnin.ru.bankservice.repositories.postgres;

import kopnin.ru.bankservice.models.postgres.Limits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitsRepositories extends JpaRepository<Limits,Long> {
}
