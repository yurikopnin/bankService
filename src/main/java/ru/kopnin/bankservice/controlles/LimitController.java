package ru.kopnin.bankservice.controlles;

import ru.kopnin.bankservice.DTO.LimitDTO;
import ru.kopnin.bankservice.models.postgres.Limit;
import ru.kopnin.bankservice.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kopnin.bankservice.util.ConvertModelAndDTO;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/limits")
public class LimitController {
    private final LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    //Добавляем новый Лимит клиенту
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addLimit(@RequestBody LimitDTO limitDTO) {
        limitService.addLimit(limitDTO, limitDTO.getBankAccountNumber());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
