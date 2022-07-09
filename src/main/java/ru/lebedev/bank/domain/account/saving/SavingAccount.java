package ru.lebedev.bank.domain.account.saving;

import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.accountPlan.AccountPlan;

import javax.persistence.*;

@Entity
@Table(name = "saving_account")
@NoArgsConstructor
public class SavingAccount extends Account {

    @Column(name = "period")
    private Long period;


    @ManyToOne
    @JoinColumn(name = "plan_id")
    private AccountPlan accountPlan;
}
