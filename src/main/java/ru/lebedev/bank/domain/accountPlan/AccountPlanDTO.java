package ru.lebedev.bank.domain.accountPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPlanDTO {
    private Long id;
    private String type;
    private BigDecimal percent;

}
