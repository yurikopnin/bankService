package kopnin.ru.bankservice.service;

import kopnin.ru.bankservice.models.postgres.Limit;
import kopnin.ru.bankservice.repositories.postgres.ClientRepositories;
import kopnin.ru.bankservice.repositories.postgres.LimitsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class LimitService {
    private final ClientRepositories clientRepositories;
    private final LimitsRepositories limitsRepositories;

    @Autowired
    public LimitService(ClientRepositories clientRepositories, LimitsRepositories limitsRepositories) {
        this.clientRepositories = clientRepositories;
        this.limitsRepositories = limitsRepositories;

    }

    public List<Limit> findAllLimit() {
        return limitsRepositories.findAll();
    }

    @Transactional
    public void addLimit(Limit limit, String bankAccountNumber) {
        limit.setClient(clientRepositories.findByBankAccountNumber(bankAccountNumber));
        limitsRepositories.save(limit);
    }
}
