package ru.kopnin.bankservice.models.postgres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
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
//    @Column(name = "password")
//    private String password;
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "client_role",
//            joinColumns = @JoinColumn(name = "client_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Role> roles = new HashSet<>();
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_client")
//    private List<Transaction> clientTransactions;
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "limit_сlient")
//    private List<Limit> clientLimits;
}
