package ru.lebedev.bank.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.lebedev.bank.domain.accountPlan.AccountPlan;
import ru.lebedev.bank.domain.client.Client;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_id_seq", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "date_opened")
    LocalDateTime dateOpened;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private AccountPlan accountPlan;
}