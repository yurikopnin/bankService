package kopnin.ru.bankservice.service;


import kopnin.ru.bankservice.DTO.LimitTransactionDTO;
import kopnin.ru.bankservice.models.postgres.Client;
import kopnin.ru.bankservice.models.postgres.Limit;
import kopnin.ru.bankservice.models.postgres.Transaction;
import kopnin.ru.bankservice.repositories.postgres.LimitsRepositories;
import kopnin.ru.bankservice.repositories.postgres.TransactionsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public TransactionService(TransactionsRepositories transactionsRepositories,
                              ClientService clientService,
                              LimitsRepositories limitsRepositories, ConversionService conversionService, EntityManager entityManager) {
        this.transactionsRepositories = transactionsRepositories;
        this.clientService = clientService;
        this.limitsRepositories = limitsRepositories;
        this.conversionService = conversionService;
        this.entityManager = entityManager;
    }

    //Метод возвращает список TransactionLimitDTO, которые превысили лимит в дипазоне дат,
// если в метод не передаются параметры, то возвращаются все TransactionLimitDTO, которые превысили лимит
    public List<LimitTransactionDTO> getLimitWithExceededTransaction(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime_from = LocalDateTime.parse(from, formatter);
        LocalDateTime localDateTime_to = LocalDateTime.parse(to, formatter);
        List<Transaction> transactions;
        if (from.equals(null) || to.equals(null)) {
            Query query = entityManager.createQuery("from Transaction where limitExceeded=TRUE "
                    , Transaction.class);
            transactions = query.getResultList();

        } else {
            Query query = entityManager.createQuery("from Transaction where limitExceeded=TRUE AND " +
                    "datetime>:param1 AND datetime<:param2", Transaction.class);
            query.setParameter("param1", localDateTime_from);
            query.setParameter("param2", localDateTime_to);
            transactions = query.getResultList();
        }
        return transactions.stream()
                .map(this::convertToLimitTransactionDTO)
                .collect(Collectors.toList());

    }

    //Метод сохранения транзакции
    @Transactional
    public void addTransaction(Transaction transaction) {
        //Получаем лимит при поступлении информации от транцазкции
        Query query = entityManager.createQuery("from Limit where limitExpenseCategory = :paramName1" +
                        " and client.bankAccountNumber = :paramName2  order by limitDateTime DESC",
                Limit.class);
        query.setParameter("paramName1", transaction.getExpenseCategory());
        query.setParameter("paramName2", transaction.getAccountFrom());
        Limit limit = (Limit) query.getResultStream().findFirst().orElse(null);//Обработать с учетом null и метода ifPresent
        System.out.println(limit);
        //Пеобразуем валюту транзакции к валюте лимита, валютой лимита является USD, валютой транзакции могут быть: EUR и BTC
        BigDecimal sumAfterConvert = null;//Добавить проверку на null
        if (transaction.getCurrencyShortname().equals("EUR")) {
            BigDecimal eurRate = conversionService.getConversion("EUR/USD").getRate();
            sumAfterConvert = transaction.getSum().multiply(eurRate);
        } else if (transaction.getCurrencyShortname().equals("BTC")) {
            BigDecimal btcRate = conversionService.getConversion("BTC/USD").getRate();
            sumAfterConvert = transaction.getSum().multiply(btcRate);
        }
        //Сравниваем значение поля remainingMonthLimit сущности Лимит и поле sum сущности транзакция
        BigDecimal difference = limit.getRemainingMonthLimit().subtract(sumAfterConvert);
        if ((difference.compareTo(BigDecimal.valueOf(0)) < 0)) {
            transaction.setLimitExceeded(true);
            transaction.setLimit(limit);
        } else {
            transaction.setLimitExceeded(false);
        }
        //Обновляем значение поля remainingMonthLimit сущности Лимит
        limit.setRemainingMonthLimit(difference);
        //Обновляем сущность лимит
        entityManager.merge(limit);
        //Сохраняем сущность транзакции
        Client client = clientService.findByBankAccountNumber(transaction.getAccountFrom()).stream().findAny().orElse(null);
        transaction.setClient(client);
        transactionsRepositories.save(transaction);

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
}
