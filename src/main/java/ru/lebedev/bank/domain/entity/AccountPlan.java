package ru.lebedev.bank.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account_plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_plan_gen")
    @SequenceGenerator(name = "account_plan_gen", sequenceName = "account_id_seq", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "percent")
    private BigDecimal percent;
}
