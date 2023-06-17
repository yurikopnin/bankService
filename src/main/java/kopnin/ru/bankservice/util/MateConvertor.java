package kopnin.ru.bankservice.util;

import kopnin.ru.bankservice.DTO.ConversionDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class MateConvertor {
    public static LocalDateTime mateAtConvertor(ConversionDTO conversionDTO) {
        final DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        final long unixTime = conversionDTO.getTimestamp();
        final String formattedDtm = Instant.ofEpochSecond(unixTime)
                .atZone(ZoneId.of("GMT+3"))
                .format(formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(formattedDtm, formatter);
        return localDateTime;


    }
}
