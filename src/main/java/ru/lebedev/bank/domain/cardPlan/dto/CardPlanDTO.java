package ru.lebedev.bank.domain.cardPlan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardPlanDTO {
    private Long id;
    @NotNull
    private Boolean isCredit;
    @NotNull
    private BigDecimal percent;
}
