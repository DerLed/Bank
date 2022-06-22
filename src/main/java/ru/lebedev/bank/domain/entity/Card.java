package ru.lebedev.bank.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "pin")
    private String pin;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private CardPlan cardPlan;
}
