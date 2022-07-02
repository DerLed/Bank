package ru.lebedev.bank.domain.cardPlan;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CardPlanDTO {
    private Long id;
    private Boolean isCredit;
    private BigDecimal percent;
}
