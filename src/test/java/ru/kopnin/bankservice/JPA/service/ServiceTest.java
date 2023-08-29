package ru.kopnin.bankservice.JPA.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kopnin.bankservice.BankserviceApplication;
import ru.kopnin.bankservice.BankserviceApplicationTests;
import ru.kopnin.bankservice.models.postgres.Client;
import ru.kopnin.bankservice.models.postgres.Limit;
import ru.kopnin.bankservice.models.postgres.Transaction;
import ru.kopnin.bankservice.repositories.postgres.ClientRepositories;
import ru.kopnin.bankservice.repositories.postgres.LimitsRepositories;
import ru.kopnin.bankservice.repositories.postgres.TransactionsRepositories;
import ru.kopnin.bankservice.service.ClientService;
import ru.kopnin.bankservice.service.LimitService;
import ru.kopnin.bankservice.service.TransactionService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = BankserviceApplication.class)
@ExtendWith(SpringExtension.class)
public class ServiceTest {
    @Mock
    private LimitsRepositories limitsRepositories;
    @Mock
    private ClientRepositories clientRepositories;
    @Mock
    private TransactionsRepositories transactionsRepositories;
    @InjectMocks
    private LimitService limitService;
    @InjectMocks
    private ClientService clientService;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void saveClientTest() {
        Client client = new Client();
        client.setBankAccountNumber("12121212121212");
        clientService.addClient(client);
        verify(this.clientRepositories.save(client));
    }
}
