package ru.kopnin.bankservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ConversionErrorResponse {
    private String message;
    private long timestamp;
}
