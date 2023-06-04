package kopnin.ru.bankservice.models.postgres;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = " Field may not be empty")
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_client")
    private List<Transaction> clientTransactions;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "limit_client")
    private List<Limit> clientLimits;


}
