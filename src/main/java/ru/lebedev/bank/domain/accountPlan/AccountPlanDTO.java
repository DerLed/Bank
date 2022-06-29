package ru.lebedev.bank.domain.accountPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPlanDTO {
    private Long id;

    @NotBlank
    private String type;

    @PositiveOrZero
    private BigDecimal percent;

}
