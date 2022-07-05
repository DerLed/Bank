package ru.lebedev.bank.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.lebedev.bank.domain.accountPlan.AccountPlan;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.client.Client;
import ru.lebedev.bank.utills.AccountNumberGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_id_seq", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "date_opened")
    @CreatedDate
    LocalDateTime dateOpened;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    private Client client;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private AccountPlan accountPlan;

    @PrePersist
    private void create() {

        if(isClosed == null) isClosed = false;

        if (accountNumber == null) {
            accountNumber = AccountNumberGenerator.genNumberAccount(this);
        }

        if(amount == null)
            amount = BigDecimal.ZERO;
        if (accountPlan.getType().equals(TypeAccount.SAVING)){
            isDefault = false;
        }
        if (accountPlan.getType().equals(TypeAccount.LOAN)){
            isDefault = false;
        }
    }
}
