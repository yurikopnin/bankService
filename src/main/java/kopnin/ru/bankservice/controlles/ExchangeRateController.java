package kopnin.ru.bankservice.controlles;

import feign.FeignException;
import feign.Param;
import kopnin.ru.bankservice.DTO.ConversionDTO;
import kopnin.ru.bankservice.client.ExchangeRateClient;
import kopnin.ru.bankservice.models.cassandra.Conversion;
import kopnin.ru.bankservice.service.ConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
@Slf4j
@RestController
@RequestMapping("/exchangerate")

public class ExchangeRateController {

    private final ExchangeRateClient exchangeRateClient;
    private final ConversionService conversionService;

    @Autowired
    public ExchangeRateController(ExchangeRateClient exchangeRateClient, ConversionService conversionService) {
        this.exchangeRateClient = exchangeRateClient;
        this.conversionService = conversionService;
    }


    @GetMapping("/{symbol1}/{symbol2}")
    //
    public String getRate(@PathVariable("symbol1") String symbol1,
                          @PathVariable("symbol2") String symbol2) {
        System.out.println(symbol1 + "/" + symbol2);
        String symbol = symbol1 + "/" + symbol2;

        return exchangeRateClient.getExchangeRate(symbol).getStatusCode().toString();

    }


}

