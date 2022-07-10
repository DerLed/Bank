package ru.lebedev.bank.domain.account.saving;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import ru.lebedev.bank.domain.account.Account;
import ru.lebedev.bank.domain.accountPlan.AccountPlan;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "saving_account")
@NoArgsConstructor
@Getter
@Setter
public class SavingAccount extends Account {

    @Column(name = "period")
    private Long period;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private AccountPlan accountPlan;
}
