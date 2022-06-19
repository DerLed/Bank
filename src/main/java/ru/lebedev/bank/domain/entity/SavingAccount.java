package ru.lebedev.bank.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "saving_account")
public class SavingAccount extends BaseAccount{

    @Column(name = "rate")
    BigDecimal rate;
}
