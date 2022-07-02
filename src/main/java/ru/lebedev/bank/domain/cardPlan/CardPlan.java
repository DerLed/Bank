package ru.lebedev.bank.domain.cardPlan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "card_plan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_plan_gen")
    @SequenceGenerator(name = "card_plan_gen", sequenceName = "card_plan_id_seq", allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "is_credit")
    private Boolean isCredit;

    @Column(name = "percent")
    private BigDecimal percent;
}
