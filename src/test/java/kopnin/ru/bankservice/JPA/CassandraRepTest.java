package kopnin.ru.bankservice.JPA;

import kopnin.ru.bankservice.config.CassandraConfiguration;
import kopnin.ru.bankservice.config.CassandraConfigurationProperties;
import kopnin.ru.bankservice.models.cassandra.Conversion;
import kopnin.ru.bankservice.repositories.cassandra.ConversionsRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Import(CassandraConfigurationProperties.class)
@Import({CassandraConfiguration.class, CassandraConfigurationProperties.class})
public class CassandraRepTest {
    @Autowired
    private ConversionsRepositories conversionsRepositories;


    @Test
    public void saveConversionTest() {
        Conversion conversion = new Conversion();
        conversion.setRate(BigDecimal.valueOf(100));
        conversion.setRateOnPreviousClose(BigDecimal.valueOf(100));
        conversion.setMadeAt(LocalDateTime.now());
        conversion.setSymbol("USD");

        conversionsRepositories.save(conversion);
        Assertions.assertNotNull(conversion.getId());
    }
}
