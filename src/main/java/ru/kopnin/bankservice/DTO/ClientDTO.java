package ru.kopnin.bankservice.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientDTO {
    @NotEmpty(message = " Field may not be empty")
    private String bankAccountNumber;


}
