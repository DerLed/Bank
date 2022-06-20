package ru.lebedev.bank.domain.entity.account;

import javax.persistence.*;

@Entity
@Table(name = "checking_account")
public class CheckingAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checking_account_gen")
    @SequenceGenerator(name = "checking_account_gen", sequenceName = "checking_account_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "acc_id")
    private BaseAccount baseAccount;

}
