package ru.kopnin.bankservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kopnin.bankservice.DTO.ConversionDTO;
import ru.kopnin.bankservice.client.ExchangeRateClient;
import ru.kopnin.bankservice.models.cassandra.Conversion;
import ru.kopnin.bankservice.models.cassandra.ConversionKey;
import ru.kopnin.bankservice.util.MateConvertor;
import ru.kopnin.bankservice.util.RateNotFoundException;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConversionService {
    private final CassandraRepository cassandraRepository;
    private final ExchangeRateClient exchangeRateClient;
    private final CassandraTemplate cassandraTemplate;

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

