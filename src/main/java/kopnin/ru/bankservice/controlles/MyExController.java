package kopnin.ru.bankservice.controlles;

import kopnin.ru.bankservice.util.ConversionErrorResponce;
import kopnin.ru.bankservice.util.RateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExController {

    @ExceptionHandler
    public ResponseEntity<ConversionErrorResponce> handleException(RateNotFoundException e) {
        ConversionErrorResponce conversionErrorResponce = new ConversionErrorResponce(
                "Сurrency pairs not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(conversionErrorResponce, HttpStatus.NOT_FOUND);
    }

}
