package ru.lebedev.bank.domain.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.cardPlan.CardPlan;
import ru.lebedev.bank.domain.client.Client;

import javax.persistence.*;


@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_gen")
    @SequenceGenerator(name = "card_gen", sequenceName = "card_id_seq", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "pin")
    private String pin;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private CardPlan cardPlan;


}
