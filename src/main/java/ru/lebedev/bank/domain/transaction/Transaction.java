package ru.lebedev.bank.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.TransactionStatus;
import ru.lebedev.bank.domain.account.Account;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_gen")
    @SequenceGenerator(name = "transaction_gen", sequenceName = "transaction_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account souseAccount;

    @ManyToOne
    @JoinColumn(name = "target_account_id")
    private Account targetAccount;

}
