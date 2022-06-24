package ru.lebedev.bank.domain.cardPlan;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardPlanDTO {
    private Long id;
    private Boolean isCredit;
    private BigDecimal percent;
}
