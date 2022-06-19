package ru.lebedev.bank.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_gen")
    @SequenceGenerator(name = "transaction_gen", sequenceName = "transaction_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private BaseAccount sourceAccount;

    @ManyToOne
    @JoinColumn(name = "receipt_account_id")
    private BaseAccount receiptAccount;


}
