package ru.lebedev.bank.domain.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.cardPlan.CardPlan;
import ru.lebedev.bank.domain.client.Client;

import javax.persistence.*;

@Entity
@Table(name = "loan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_gen")
    @SequenceGenerator(name = "loan_gen", sequenceName = "loan_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @ManyToOne
    @JoinColumn(name = "linked_account_id")
    private Account linkedAccount;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "plan_id")
//    private CardPlan cardPlan;


}
