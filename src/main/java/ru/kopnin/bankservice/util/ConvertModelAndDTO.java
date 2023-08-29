package ru.kopnin.bankservice.util;

import ru.kopnin.bankservice.DTO.ClientDTO;
import ru.kopnin.bankservice.DTO.LimitDTO;
import ru.kopnin.bankservice.DTO.TransactionDTO;
import ru.kopnin.bankservice.models.postgres.Client;
import ru.kopnin.bankservice.models.postgres.Limit;
import ru.kopnin.bankservice.models.postgres.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ConvertModelAndDTO {


    private static ModelMapper modelMapper;

    @Autowired
    public ConvertModelAndDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    //Преобразуем DTO лимита в класс лимита



}
