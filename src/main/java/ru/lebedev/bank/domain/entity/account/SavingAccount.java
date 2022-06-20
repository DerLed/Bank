package ru.lebedev.bank.domain.entity.account;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "saving_account")
public class SavingAccount{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saving_account_gen")
    @SequenceGenerator(name = "saving_account_gen", sequenceName = "saving_account_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "rate")
    BigDecimal rate;

    @OneToOne
    @JoinColumn(name = "acc_id")
    private BaseAccount baseAccount;
}
