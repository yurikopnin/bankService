package kopnin.ru.bankservice.models;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="client")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(name = "bankAccountNumber")
    private String bankAccountNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="transactionClient")
    private List<Transactions> clientTransactions;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="limitClient")
    private List<Limits> clientLimits;
//Конструктор без параметров
    public Client() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public List<Transactions> getClientTransactions() {
        return clientTransactions;
    }

    public void setClientTransactions(List<Transactions> clientTransactions) {
        this.clientTransactions = clientTransactions;
    }

    public List<Limits> getClientLimits() {
        return clientLimits;
    }

    public void setClientLimits(List<Limits> clientLimits) {
        this.clientLimits = clientLimits;
    }
}
