package kopnin.ru.bankservice.client;

import feign.Param;
import kopnin.ru.bankservice.DTO.ConversionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange",url = "https://api.twelvedata.com/exchange_rate")
//, url = "https://api.twelvedata.com/exchange_rate?symbol={symbol}&apikey={apikey}"
public interface ExchangeRateClient {

    @RequestMapping(method = RequestMethod.GET,value = "?symbol={symbol}&apikey={apikey}")
    ResponseEntity<ConversionDTO> getExchangeRate(@RequestParam(value = "symbol") String symbol,
                                                  @RequestParam(value = "apikey") String apikey);


}





