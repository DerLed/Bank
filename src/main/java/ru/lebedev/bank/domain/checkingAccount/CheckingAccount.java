package ru.lebedev.bank.domain.checkingAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lebedev.bank.domain.account.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "checking_account")
@NoArgsConstructor
@Getter
@Setter
public class CheckingAccount extends Account {
    //Является ли данный счет счетом по умолчанию
    @Column(name = "is_default")
    private Boolean isDefault;

}
