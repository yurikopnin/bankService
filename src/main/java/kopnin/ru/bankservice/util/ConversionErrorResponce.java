package kopnin.ru.bankservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ConversionErrorResponce {
    private String message;
    private long timestamp;
}
