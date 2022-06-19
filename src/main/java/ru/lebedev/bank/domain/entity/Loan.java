package ru.lebedev.bank.domain.entity;

import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_gen")
    @SequenceGenerator(name = "loan_gen", sequenceName = "loan_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "loan_period")
    private Integer loanPeriod;

    @Column(name = "date_opened")
    private LocalDateTime dateOpened;

    @Column(name = "month_repayment")
    private BigDecimal monthRepayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @ToString.Exclude
    private CheckingAccount account;

}
