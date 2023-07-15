package kopnin.ru.bankservice.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
@Data
public class ClientDTO {
    @NotEmpty(message = " Field may not be empty")
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
}
