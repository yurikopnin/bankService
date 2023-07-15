package kopnin.ru.bankservice.controlles;

import kopnin.ru.bankservice.client.ExchangeRateClient;
import kopnin.ru.bankservice.models.cassandra.Conversion;
import kopnin.ru.bankservice.service.ClientService;
import kopnin.ru.bankservice.service.ConversionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@RestController
@RequestMapping("/exchangerate")

public class ExchangeRateController {

    private final ExchangeRateClient exchangeRateClient;
    private final ConversionService conversionService;
    private final ClientService clientService;

    private final ModelMapper modelMapper;

    @Autowired
    public ExchangeRateController(ExchangeRateClient exchangeRateClient, ConversionService conversionService, ClientService clientService, ModelMapper modelMapper) {
        this.exchangeRateClient = exchangeRateClient;
        this.conversionService = conversionService;
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/{symbol1}/{symbol2}")

    public Conversion getRate(@PathVariable("symbol1") String symbol1,
                              @PathVariable("symbol2") String symbol2) {
        System.out.println(symbol1 + "/" + symbol2);
        String symbol = symbol1 + "/" + symbol2;


        return conversionService.getConversion(symbol);

    }





}

