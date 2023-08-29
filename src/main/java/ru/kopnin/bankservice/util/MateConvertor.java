package ru.kopnin.bankservice.util;

import ru.kopnin.bankservice.DTO.ConversionDTO;

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
                .atZone(ZoneId.of("GMT+0"))
                .format(formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(formattedDtm, formatter);
        return localDateTime;
    }
}
