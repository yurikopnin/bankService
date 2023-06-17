package kopnin.ru.bankservice.JPA;

import kopnin.ru.bankservice.models.cassandra.Conversion;
import kopnin.ru.bankservice.models.postgres.Client;
import kopnin.ru.bankservice.models.postgres.Limit;
import kopnin.ru.bankservice.models.postgres.Transaction;
import kopnin.ru.bankservice.repositories.cassandra.ConversionsRepositories;
import kopnin.ru.bankservice.repositories.postgres.ClientRepositories;
import kopnin.ru.bankservice.repositories.postgres.LimitsRepositories;
import kopnin.ru.bankservice.repositories.postgres.TransactionsRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class RepositoryTest {

    private final ClientRepositories clientRepositories;
    private final LimitsRepositories limitsRepositories;
    private final TransactionsRepositories transactionsRepositories;

    @Autowired
    public RepositoryTest(ClientRepositories clientRepositories,
                          TransactionsRepositories transactionsRepositories,
                          LimitsRepositories limitsRepositories) {
        this.clientRepositories = clientRepositories;
        this.transactionsRepositories = transactionsRepositories;
        this.limitsRepositories = limitsRepositories;

    }




    @Test
    public void saveClientTest() {
        Client client = new Client();
        client.setBankAccountNumber("123456789");

        Assertions.assertNull(client.getId());
        clientRepositories.save(client);
        Assertions.assertNotNull(client.getId());


    }

    @Test
    public void saveLimitTest(){
        Limit limit = new Limit();
        limit.setLimitSum(BigDecimal.valueOf(30000));
        limit.setRemainingMonthLimit(BigDecimal.valueOf(10000));
        limit.setLimitDateTime(LocalDateTime.now());
        limit.setLimitCurrencyShortname("USD");
        limit.setLimitExpenseCategory("product");
        limit.setLimitClient(10L);
        limit.setLimitTransactions(null);

        Assertions.assertNull(limit.getId());
        limitsRepositories.save(limit);
        Assertions.assertNotNull(limit.getId());
    }

    @Test
    public void saveTransactionTest(){


        Transaction transaction = new Transaction();
        transaction.setAccountFrom("1000000000");
        transaction.setAccountTo("2000000000");
        transaction.setSum(BigDecimal.valueOf(10000));
        transaction.setCurrencyShortname("USD");
        transaction.setExpenseCategory("product");
        transaction.setDatetime(LocalDateTime.now());
        transaction.setLimitExceeded(true);
        transaction.setTransactionClient(1L);
        transaction.setTransactionLimit(1L);

        Assertions.assertNull(transaction.getId());
        transactionsRepositories.save(transaction);
        Assertions.assertNotNull(transaction.getId());


    }



}
