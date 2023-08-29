package ru.kopnin.bankservice.service;

import org.modelmapper.ModelMapper;
import ru.kopnin.bankservice.DTO.LimitDTO;
import ru.kopnin.bankservice.models.postgres.Limit;
import ru.kopnin.bankservice.repositories.postgres.ClientRepositories;
import ru.kopnin.bankservice.repositories.postgres.LimitsRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LimitService {
    private final ClientRepositories clientRepositories;
    private final LimitsRepositories limitsRepositories;
    private final ModelMapper modelMapper;

    public LimitService(ClientRepositories clientRepositories, LimitsRepositories limitsRepositories, ModelMapper modelMapper) {
        this.clientRepositories = clientRepositories;
        this.limitsRepositories = limitsRepositories;
        this.modelMapper = modelMapper;
    }

    public List<Limit> findAllLimit() {
        return limitsRepositories.findAll();
    }

    @Transactional
    public void addLimit(LimitDTO limitDTO, String bankAccountNumber) {
        Limit limit = convertToLimit(limitDTO);
        limit.setLimitDateTime(LocalDateTime.now());
        limit.setRemainingMonthLimit(limitDTO.getLimitSum());
        limit.setClient(clientRepositories.findByBankAccountNumber(bankAccountNumber));
        limitsRepositories.save(limit);
    }
    public  Limit convertToLimit(LimitDTO limitDTO) {
        Limit limit = modelMapper.map(limitDTO, Limit.class);
        return limit;
    }
}
