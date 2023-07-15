package kopnin.ru.bankservice.service;

import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopnin.ru.bankservice.DTO.ConversionDTO;
import kopnin.ru.bankservice.client.ExchangeRateClient;
import kopnin.ru.bankservice.models.cassandra.Conversion;
import kopnin.ru.bankservice.models.cassandra.ConversionKey;
import kopnin.ru.bankservice.util.MateConvertor;
import kopnin.ru.bankservice.util.RateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ConversionService {
    private final CassandraRepository cassandraRepository;
    private final ExchangeRateClient exchangeRateClient;
    private final CassandraTemplate cassandraTemplate;

    @Autowired
    public ConversionService(CassandraRepository cassandraRepository, ExchangeRateClient exchangeRateClient, CassandraTemplate cassandraTemplate) {
        this.cassandraRepository = cassandraRepository;
        this.exchangeRateClient = exchangeRateClient;
        this.cassandraTemplate = cassandraTemplate;
    }

    public Conversion getConversion(String symbol) {
        if ((symbol.equals("EUR/USD")) | (symbol.equals("BTC/USD"))) {
            Conversion conversion = new Conversion();
            if (symbol.equals("EUR/USD")) {
                String select = "SELECT * FROM conversions WHERE symbol = 'EUR/USD' ORDER BY made_at DESC LIMIT 1";
                conversion = cassandraTemplate.select(select, Conversion.class).stream().findFirst().orElse(null);
            } else if (symbol.equals("BTC/USD")) {
                String select = "SELECT * FROM conversions WHERE symbol = 'BTC/USD' ORDER BY made_at DESC LIMIT 1";
                conversion = cassandraTemplate.select(select, Conversion.class).stream().findFirst().orElse(null);
            }
            return conversion;
        } else throw new RateNotFoundException();

    }

    @Scheduled(cron = "0 45 16 * * *")
    @Transactional
    public void addConversionEUR() {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpStatus httpStatus = exchangeRateClient.getExchangeRate("EUR/USD").getStatusCode();
        if (httpStatus.is2xxSuccessful()) {
            try {
                ConversionKey conversionKey = new ConversionKey();
                Conversion conversion = new Conversion();
                ConversionDTO conversionDTO = objectMapper.readValue(exchangeRateClient.getExchangeRate("EUR/USD").getBody(),
                        ConversionDTO.class);
                conversionKey.setSymbol(conversionDTO.getSymbol());
                conversionKey.setMadeAt(MateConvertor.mateAtConvertor(conversionDTO));
                conversion.setRate(conversionDTO.getRate());
                conversion.setRateOnPreviousClose(conversionDTO.getRate());
            } catch (JsonProcessingException e) {
                e.getMessage();
            }

        } else if (httpStatus.is4xxClientError() | httpStatus.is5xxServerError()) {

            String select = "SELECT * FROM conversions WHERE symbol = 'EUR/USD' ORDER BY made_at DESC LIMIT 1";
            Conversion conversion = cassandraTemplate.select(select, Conversion.class).stream().findFirst().orElse(null);
            conversion.setRate(conversion.getRateOnPreviousClose());
            cassandraTemplate.update(conversion);
            System.out.println(conversion);

        }
    }

    @Scheduled(cron = "0 45 16 * * *")
    @Transactional
    public void addConversionBTC() {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpStatus httpStatus = exchangeRateClient.getExchangeRate("BTC/USD").getStatusCode();
        if (httpStatus.is2xxSuccessful()) {
            try {
                ConversionKey conversionKey = new ConversionKey();
                Conversion conversion = new Conversion();
                ConversionDTO conversionDTO = objectMapper.readValue(exchangeRateClient.getExchangeRate("BTC/USD").getBody(),
                        ConversionDTO.class);
                conversionKey.setSymbol(conversionDTO.getSymbol());
                conversionKey.setMadeAt(MateConvertor.mateAtConvertor(conversionDTO));
                conversion.setRate(conversionDTO.getRate());
                conversion.setRateOnPreviousClose(conversionDTO.getRate());
            } catch (JsonProcessingException e) {
                e.getMessage();
            }

        } else if (httpStatus.is4xxClientError() | httpStatus.is5xxServerError()) {
            String select = "SELECT * FROM conversions WHERE symbol = 'EUR/USD' ORDER BY made_at DESC LIMIT 1";
            Conversion conversion = cassandraTemplate.select(select, Conversion.class).stream().findFirst().orElse(null);
            conversion.setRate(conversion.getRateOnPreviousClose());
            cassandraTemplate.update(conversion);
            System.out.println(conversion);

        }
    }

}

