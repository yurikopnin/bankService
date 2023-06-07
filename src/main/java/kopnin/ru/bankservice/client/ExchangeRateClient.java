package kopnin.ru.bankservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "exchange", url = "${client.exchange.baseUrl}")
public interface ExchangeRateClient {
}





