package ru.kopnin.bankservice.controlles;

import ru.kopnin.bankservice.util.ConversionErrorResponse;
import ru.kopnin.bankservice.util.RateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExController {
    @ExceptionHandler
    public ResponseEntity<ConversionErrorResponse> handleException(RateNotFoundException e) {
        ConversionErrorResponse conversionErrorResponce = new ConversionErrorResponse(
                "Сurrency pairs not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(conversionErrorResponce, HttpStatus.NOT_FOUND);
    }

}
