package ru.lebedev.bank.domain.accountPlan;

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
    @SequenceGenerator(name = "account_plan_gen", sequenceName = "account_plan_id_seq", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "percent")
    private BigDecimal percent;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private TypeAccount type;
}
