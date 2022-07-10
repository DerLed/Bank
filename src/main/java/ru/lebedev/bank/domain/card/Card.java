package ru.lebedev.bank.domain.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.client.Client;

import javax.persistence.*;


@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    @PrePersist
    private void create() {

        if(isClosed == null) isClosed = false;
        if(isBlocked == null) isBlocked = false;

    }


}
