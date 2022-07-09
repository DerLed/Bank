package ru.lebedev.bank.domain.account.checking;

import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.account.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "checking_account")
@NoArgsConstructor
public class CheckingAccount extends Account {
    //Является ли данный счет счетом по умолчанию
    @Column(name = "is_default")
    private Boolean isDefault;

}
