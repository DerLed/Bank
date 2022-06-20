package ru.lebedev.bank.domain.entity.account;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "loan_account")
public class LoanAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_gen")
    @SequenceGenerator(name = "loan_gen", sequenceName = "loan_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "loan_period")
    private Integer loanPeriod;

    @Column(name = "month_repayment")
    private BigDecimal monthRepayment;

    @OneToOne
    @JoinColumn(name = "acc_id")
    private BaseAccount baseAccount;

}
