package kopnin.ru.bankservice.service;

import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import kopnin.ru.bankservice.client.ExchangeRateClient;
import kopnin.ru.bankservice.models.cassandra.Conversion;
import kopnin.ru.bankservice.models.cassandra.ConversionKey;
import kopnin.ru.bankservice.util.MateConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ConversionService {
    //Сделать тоде самое для 2-ого метода
    private static final String USD_EUR_SYMBOL = "USD/EUR";

    private final CassandraRepository cassandraRepository;

    private final ExchangeRateClient exchangeRateClient;


    private final CassandraTemplate cassandraTemplate;

    @Autowired
    public ConversionService(CassandraRepository cassandraRepository, ExchangeRateClient exchangeRateClient, CassandraTemplate cassandraTemplate) {
        this.cassandraRepository = cassandraRepository;

        this.exchangeRateClient = exchangeRateClient;

        this.cassandraTemplate = cassandraTemplate;
    }

    @Transactional
    public void addConversion() {


//        if (exchangeRateClient.getExchangeRate("EUR/RUB", "demo").getStatusCode().is2xxSuccessful()) {
//
//
//            ConversionKey conversionKey = new ConversionKey();
//            Conversion conversion = new Conversion();
//            conversionKey.setMadeAt(MateConvertor.mateAtConvertor(exchangeRateClient.getExchangeRate("EUR/USD", "demo").getBody()));
//            conversionKey.setSymbol(exchangeRateClient.getExchangeRate("EUR/USD", "demo").getBody().getSymbol());
//            conversion.setKey(conversionKey);
//            conversion.setRate(exchangeRateClient.getExchangeRate("EUR/USD", "demo").getBody().getRate());
//            conversion.setRateOnPreviousClose(exchangeRateClient.getExchangeRate("EUR/USD", "demo").getBody().getRate());
//            cassandraRepository.save(conversion);
//
//
//        } else if (exchangeRateClient.getExchangeRate("EUR/RUB", "demo").getStatusCode().is4xxClientError() |
//                exchangeRateClient.getExchangeRate("EUR/RUB", "demo").getStatusCode().is5xxServerError()) {
//
//            String select = "SELECT * FROM conversions WHERE symbol = 'EUR/USD' ORDER BY made_at DESC LIMIT 1";
//            Conversion conversion = cassandraTemplate.select(select, Conversion.class).stream().findFirst().orElse(null);
//            conversion.setRate(conversion.getRateOnPreviousClose());
//           // conversion.setRate(BigDecimal.valueOf(2));
//            cassandraTemplate.update(conversion);
//            System.out.println(conversion);
//
//        }


    }
}

