package kopnin.ru.bankservice.repositories.postgres;

import kopnin.ru.bankservice.models.postgres.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositories extends JpaRepository<Client, Long> {

    public Client findByBankAccountNumber(String bankAccountNumber);
}
