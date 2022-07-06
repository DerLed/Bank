package ru.lebedev.bank.domain.loan;

import javax.persistence.*;

public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_gen")
    @SequenceGenerator(name = "loan_gen", sequenceName = "loan_id_seq", allocationSize = 1)
    @Column(name = "id")
    Long id;


}
