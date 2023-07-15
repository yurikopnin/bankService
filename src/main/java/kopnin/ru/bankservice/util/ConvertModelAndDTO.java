package kopnin.ru.bankservice.util;

import kopnin.ru.bankservice.DTO.ClientDTO;
import kopnin.ru.bankservice.DTO.LimitDTO;
import kopnin.ru.bankservice.DTO.TransactionDTO;
import kopnin.ru.bankservice.models.postgres.Client;
import kopnin.ru.bankservice.models.postgres.Limit;
import kopnin.ru.bankservice.models.postgres.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertModelAndDTO {
    private  static ModelMapper modelMapper;

    @Autowired
    public  ConvertModelAndDTO(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    //Преобразуем DTO клиента в класс клиента
    public static Client convertToClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        return client;
    }

    //Преобразуем объект клиента в DTO клиента
    public static ClientDTO convertToClientDTO(Client client) {
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
        return clientDTO;
    }

    //Преобразуем DTO лимита в класс лимита
    public static Limit convertToLimit(LimitDTO limitDTO) {
        Limit limit = modelMapper.map(limitDTO, Limit.class);
        return limit;
    }

    //Преобразуем DTO транзакции в класс транзакции
    public static Transaction convertToTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(transactionDTO.getTransactionDateTime(),formatter);
        transaction.setDatetime(localDateTime);
        return transaction;
    }
}
