package ru.lebedev.bank.domain.entity;

import ru.lebedev.bank.domain.entity.account.BaseAccount;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_gen")
    @SequenceGenerator(name = "transaction_gen", sequenceName = "transaction_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "source_acc_id")
    private BaseAccount souseAccount;

    @ManyToOne
    @JoinColumn(name = "receipt_acc_id")
    private BaseAccount receiptAccount;

}