package kopnin.ru.bankservice.controlles;

import kopnin.ru.bankservice.DTO.LimitDTO;
import kopnin.ru.bankservice.models.postgres.Limit;
import kopnin.ru.bankservice.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import static kopnin.ru.bankservice.util.ConvertModelAndDTO.convertToLimit;

@RestController
@RequestMapping("/limits")
public class LimitController {
    private final LimitService limitService;


    @Autowired
    public LimitController(LimitService limitService) {
        this.limitService = limitService;

    }

    //Добавляем новый Лимит клиенту
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addLimit(@RequestBody LimitDTO limitDTO) {
        Limit limit = convertToLimit(limitDTO);
        limit.setLimitDateTime(LocalDateTime.now());
        limit.setRemainingMonthLimit(limitDTO.getLimitSum());
        limitService.addLimit(limit, limitDTO.getBankAccountNumber());
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
