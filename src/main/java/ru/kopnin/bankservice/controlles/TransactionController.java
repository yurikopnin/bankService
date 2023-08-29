package ru.kopnin.bankservice.controlles;

import ru.kopnin.bankservice.DTO.LimitTransactionDTO;
import ru.kopnin.bankservice.DTO.TransactionDTO;
import ru.kopnin.bankservice.models.postgres.Transaction;
import ru.kopnin.bankservice.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/limitexceeded/{symbol1}/{symbol2}")
    public List<LimitTransactionDTO> testService(@PathVariable("symbol1") String from,
                                                 @PathVariable("symbol2") String to) {
        return transactionService.getLimitWithExceededTransaction(from, to);
    }
    @GetMapping("/limitexceeded")
    public List<LimitTransactionDTO> testService() {
        return transactionService.getLimitWithExceededTransaction("","");
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.addTransaction(transactionDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
