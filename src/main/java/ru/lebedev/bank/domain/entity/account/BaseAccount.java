package ru.lebedev.bank.domain.entity.account;

import lombok.ToString;
import ru.lebedev.bank.domain.entity.Client;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class BaseAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_id_seq", allocationSize = 1)
    @Column(name = "id")
    Long id;
    @Column(name = "balance")
    BigDecimal balance;

    @Column(name = "date_opened")
    LocalDateTime dateOpened;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;

}
