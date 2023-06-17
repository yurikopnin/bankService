package kopnin.ru.bankservice.controlles;

import feign.Param;
import kopnin.ru.bankservice.DTO.ConversionDTO;
import kopnin.ru.bankservice.client.ExchangeRateClient;
import kopnin.ru.bankservice.models.cassandra.Conversion;
import kopnin.ru.bankservice.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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


    @GetMapping("/{symbol}/{apikey}")
    //
    public String getRate(@PathVariable("symbol") String symbol,
                          @PathVariable("apikey") String apikey) {
        System.out.println(symbol+" "+apikey);


        return exchangeRateClient.getExchangeRate(symbol, apikey).getBody().toString();
    }


}

