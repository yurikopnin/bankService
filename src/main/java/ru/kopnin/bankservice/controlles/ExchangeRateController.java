package ru.kopnin.bankservice.controlles;

import ru.kopnin.bankservice.client.ExchangeRateClient;
import ru.kopnin.bankservice.models.cassandra.Conversion;
import ru.kopnin.bankservice.service.ClientService;
import ru.kopnin.bankservice.service.ConversionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    public ExchangeRateController(ExchangeRateClient exchangeRateClient, ConversionService conversionService,
                                  ClientService clientService, ModelMapper modelMapper) {
        this.exchangeRateClient = exchangeRateClient;
        this.conversionService = conversionService;
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/{symbol1}/{symbol2}")
    public Conversion getRate(@PathVariable("symbol1") String symbol1,
                              @PathVariable("symbol2") String symbol2) {
        System.out.println(symbol1 + "/" + symbol2);
        String symbol = symbol1 + "/" + symbol2;
        return conversionService.getConversion(symbol);
    }

}

