package kopnin.ru.bankservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class BankserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankserviceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }


}
