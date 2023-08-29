package ru.kopnin.bankservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "exchange", url = "https://api.twelvedata.com/exchange_rate")
public interface ExchangeRateClient {
    @RequestMapping(method = RequestMethod.GET, value = "?symbol={symbol}&apikey=demo")
    ResponseEntity<String> getExchangeRate(@RequestParam(value = "symbol") String symbol);
}





