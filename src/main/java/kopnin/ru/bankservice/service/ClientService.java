package kopnin.ru.bankservice.service;

import kopnin.ru.bankservice.models.postgres.Client;
import kopnin.ru.bankservice.models.postgres.Limit;
import kopnin.ru.bankservice.repositories.postgres.ClientRepositories;
import kopnin.ru.bankservice.repositories.postgres.LimitsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepositories clientRepositories;
    private final LimitsRepositories limitsRepositories;

    @Autowired
    public ClientService(ClientRepositories clientRepositories, LimitsRepositories limitsRepositories) {

        this.clientRepositories = clientRepositories;
        this.limitsRepositories = limitsRepositories;
    }

    // Поиск всех клиентов
    public List<Client> findAllClient() {

        return clientRepositories.findAll();
    }

    //Поиск клиента по банковскому номеру
    public Optional<Client> findByBankAccountNumber(String bankAccountNumber) {
        return Optional.ofNullable(clientRepositories.findByBankAccountNumber(bankAccountNumber));
    }

    //Добавление клиента в БД с добавлением его лимитов на продукты и услуги, валюта лимитов USD
    @Transactional
    public void addClient(Client client) {
        //Лимит услуг
        Limit limit_S = new Limit();
        limit_S.setLimitSum(BigDecimal.valueOf(0L));
        limit_S.setRemainingMonthLimit(BigDecimal.valueOf(0L));
        limit_S.setLimitDateTime(LocalDateTime.now());
        limit_S.setLimitCurrencyShortname("USD");
        limit_S.setLimitExpenseCategory("service");
        //Лимит товаров
        Limit limit_P = new Limit();
        limit_P.setLimitSum(BigDecimal.valueOf(0L));
        limit_P.setRemainingMonthLimit(BigDecimal.valueOf(0L));
        limit_P.setLimitDateTime(LocalDateTime.now());
        limit_P.setLimitCurrencyShortname("USD");
        limit_P.setLimitExpenseCategory("product");
        //Регистрация нового Клиента в БД
//        List<Limit> limitList = new ArrayList<>();
//        limitList.add(limit_S);
//        limitList.add(limit_P);
      //  client.setClientLimits(limitList);
        limit_P.setClient(client);
        limit_S.setClient(client);
        clientRepositories.save(client);
        limitsRepositories.save(limit_P);
        limitsRepositories.save(limit_S);
    }


}
