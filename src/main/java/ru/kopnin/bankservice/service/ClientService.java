package ru.kopnin.bankservice.service;

import org.modelmapper.ModelMapper;
import ru.kopnin.bankservice.DTO.ClientDTO;
import ru.kopnin.bankservice.models.postgres.Client;
import ru.kopnin.bankservice.models.postgres.Limit;
import ru.kopnin.bankservice.repositories.postgres.ClientRepositories;
import ru.kopnin.bankservice.repositories.postgres.LimitsRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepositories clientRepositories;
    private final LimitsRepositories limitsRepositories;
    private final ModelMapper modelMapper;
    public ClientService(ClientRepositories clientRepositories, LimitsRepositories limitsRepositories, ModelMapper modelMapper) {
        this.clientRepositories = clientRepositories;
        this.limitsRepositories = limitsRepositories;
        this.modelMapper = modelMapper;
    }
    // Поиск всех клиентов
    public List<Client> findAllClient() {
        return clientRepositories.findAll();
    }
    //Поиск клиента по банковскому номеру
    public Optional<Client> findByBankAccountNumber(String bankAccountNumber) {
        return Optional.ofNullable(clientRepositories.findByBankAccountNumber(bankAccountNumber));
    }
    //Добавление клиента в БД с добавлением его лимитов на продукты и услуги, валюта лимитов USD
    @Transactional
    public void addClient(Client client) {

        //Лимит услуг
        Limit limitForService = new Limit();
        limitForService.setLimitSum(BigDecimal.valueOf(0L));
        limitForService.setRemainingMonthLimit(BigDecimal.valueOf(0L));
        limitForService.setLimitDateTime(LocalDateTime.now());
        limitForService.setLimitCurrencyShortname("USD");
        limitForService.setLimitExpenseCategory("service");
        //Лимит товаров
        Limit limitForProduct = new Limit();
        limitForProduct.setLimitSum(BigDecimal.valueOf(0L));
        limitForProduct.setRemainingMonthLimit(BigDecimal.valueOf(0L));
        limitForProduct.setLimitDateTime(LocalDateTime.now());
        limitForProduct.setLimitCurrencyShortname("USD");
        limitForProduct.setLimitExpenseCategory("product");
        //Регистрация нового Клиента в БД
        limitForProduct.setClient(client);
        limitForService.setClient(client);
        clientRepositories.save(client);
        limitsRepositories.save(limitForProduct);
        limitsRepositories.save(limitForProduct);
    }
    //Преобразуем DTO клиента в класс клиента
    public Client convertToClient(ClientDTO clientDTO) {
        Client client = modelMapper.map(clientDTO, Client.class);
        return client;
    }
    //Преобразуем объект клиента в DTO клиента
    public ClientDTO convertToClientDTO(Client client) {
        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);
        return clientDTO;
    }
}
