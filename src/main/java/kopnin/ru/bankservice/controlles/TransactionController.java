package kopnin.ru.bankservice.controlles;

import kopnin.ru.bankservice.DTO.LimitTransactionDTO;
import kopnin.ru.bankservice.DTO.TransactionDTO;
import kopnin.ru.bankservice.models.postgres.Transaction;
import kopnin.ru.bankservice.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static kopnin.ru.bankservice.util.ConvertModelAndDTO.convertToTransaction;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService ) {
        this.transactionService = transactionService;
    }

    @GetMapping("/limitexceeded/{symbol1}/{symbol2}")
    public List<LimitTransactionDTO> testService(@PathVariable("symbol1") String from,
                                                 @PathVariable("symbol2") String to) {

      return transactionService.getLimitWithExceededTransaction(from, to);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = convertToTransaction(transactionDTO);
        transactionService.addTransaction(transaction);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
