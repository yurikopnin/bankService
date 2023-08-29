package ru.kopnin.bankservice.service;

import org.modelmapper.ModelMapper;
import ru.kopnin.bankservice.DTO.LimitTransactionDTO;
import ru.kopnin.bankservice.DTO.TransactionDTO;
import ru.kopnin.bankservice.models.postgres.Client;
import ru.kopnin.bankservice.models.postgres.Limit;
import ru.kopnin.bankservice.models.postgres.Transaction;
import ru.kopnin.bankservice.repositories.postgres.LimitsRepositories;
import ru.kopnin.bankservice.repositories.postgres.TransactionsRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionsRepositories transactionsRepositories;
    private final ClientService clientService;
    private final LimitsRepositories limitsRepositories;
    private final ConversionService conversionService;
   private final EntityManager entityManager;
   private final ModelMapper modelMapper;

    public TransactionService(TransactionsRepositories transactionsRepositories, ClientService clientService,
                              LimitsRepositories limitsRepositories, ConversionService conversionService,
                              EntityManager entityManager, ModelMapper modelMapper) {
        this.transactionsRepositories = transactionsRepositories;
        this.clientService = clientService;
        this.limitsRepositories = limitsRepositories;
        this.conversionService = conversionService;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    //Метод извлекает транзакции превысившие предел в диапазоне дат
    public List<LimitTransactionDTO> getLimitWithExceededTransaction(String from, String to) {
        List<Transaction> transactions;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime_from = LocalDateTime.parse(from, formatter);
        LocalDateTime localDateTime_to = LocalDateTime.parse(to, formatter);
        Query query = entityManager.createQuery("from Transaction where limitExceeded=TRUE AND " +
                "datetime>:param1 AND datetime<:param2", Transaction.class);
        query.setParameter("param1", localDateTime_from);
        query.setParameter("param2", localDateTime_to);
        transactions = query.getResultList();
        return transactions.stream()
                .map(this::convertToLimitTransactionDTO)
                .collect(Collectors.toList());
    }

    //Метод извлекает все транзакции, которые превысили лимит
    public List<LimitTransactionDTO> getAllExceededTransaction() {
        List<Transaction> transactions;
        Query query = entityManager.createQuery("from Transaction where limitExceeded=TRUE "
                , Transaction.class);
        transactions = query.getResultList();
        return transactions.stream()
                .map(this::convertToLimitTransactionDTO)
                .collect(Collectors.toList());
    }

    //Метод сохранения транзакции
    @Transactional
    public void addTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = convertToTransaction(transactionDTO);
        //Получаем лимит при поступлении информации от транцазкции
        Query query = entityManager.createQuery("from Limit where limitExpenseCategory = :paramName1" +
                        " and client.bankAccountNumber = :paramName2  order by limitDateTime DESC",
                Limit.class);
        query.setParameter("paramName1", transaction.getExpenseCategory());
        query.setParameter("paramName2", transaction.getAccountFrom());
        Optional<Limit> limit = query.getResultStream().findFirst();
        limit.ifPresent(lim -> {
            //Пеобразуем валюту транзакции к валюте лимита, валютой лимита является USD, валютой транзакции могут быть: EUR и BTC
            BigDecimal sumAfterConvert = null;//Задать вопрос по поводу обратки
            if (transaction.getCurrencyShortname().equals("EUR")) {
                BigDecimal eurRate = conversionService.getConversion("EUR/USD").getRate();
                sumAfterConvert = transaction.getSum().multiply(eurRate);
            } else if (transaction.getCurrencyShortname().equals("BTC")) {
                BigDecimal btcRate = conversionService.getConversion("BTC/USD").getRate();
                sumAfterConvert = transaction.getSum().multiply(btcRate);
            }
            //Сравниваем значение поля remainingMonthLimit сущности Лимит и поле sum сущности транзакция
            BigDecimal difference = lim.getRemainingMonthLimit().subtract(sumAfterConvert);
            if ((difference.compareTo(BigDecimal.valueOf(0)) < 0)) {
                transaction.setLimitExceeded(true);
                transaction.setLimit(lim);
            } else {
                transaction.setLimitExceeded(false);
            }
            //Обновляем значение поля remainingMonthLimit сущности Лимит
            lim.setRemainingMonthLimit(difference);
            //Обновляем сущность лимит
            entityManager.merge(lim);
            //Сохраняем сущность транзакции
            Client client = clientService.findByBankAccountNumber(transaction.getAccountFrom()).
                    stream().findAny().orElse(null);
            transaction.setClient(client);
            transactionsRepositories.save(transaction);
        });
    }

    //Преобразование Transaction в LimitTransactionDTO
    public LimitTransactionDTO convertToLimitTransactionDTO(Transaction transaction) {
        LimitTransactionDTO limitTransactionDTO = new LimitTransactionDTO();
        //Переменные сущности Лимит
        limitTransactionDTO.setLimitDateTime(transaction.getLimit().getLimitDateTime().toString());
        limitTransactionDTO.setLimitSum(transaction.getLimit().getLimitSum());
        limitTransactionDTO.setRemainingMonthLimit(transaction.getLimit().getRemainingMonthLimit());
        //Переменные сущности Транзакция
        limitTransactionDTO.setTransactionDateTime(transaction.getDatetime().toString());
        limitTransactionDTO.setTransactionSum(transaction.getSum());
        limitTransactionDTO.setLimitExceeded(transaction.isLimitExceeded());
        return limitTransactionDTO;
    }
    //Преобразуем DTO транзакции в класс транзакции
    public  Transaction convertToTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(transactionDTO.getTransactionDateTime(), formatter);
        transaction.setDatetime(localDateTime);
        return transaction;
    }
}
