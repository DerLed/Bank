package ru.lebedev.bank.domain.entity;

import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseAccount implements Account{
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

    @Override
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}
