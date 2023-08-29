package ru.kopnin.bankservice;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
@EnableFeignClients
public class BankserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankserviceApplication.class, args);
    }

    }
