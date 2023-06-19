package kopnin.ru.bankservice.client;

import feign.Param;
import feign.Response;
import kopnin.ru.bankservice.DTO.ConversionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange", url = "https://api.twelvedata.com/exchange_rate")

public interface ExchangeRateClient {

    @RequestMapping(method = RequestMethod.GET, value = "?symbol={symbol}&apikey=demo")
    ResponseEntity<ConversionDTO> getExchangeRate(@RequestParam(value = "symbol") String symbol);


}





