package ru.lebedev.bank.domain.accountPlan;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountPlanDTO {
    private Long id;

    @NotBlank(message = "Поле тип не заполнено")
    private String type;

    @NotNull(message = "Проценты заполнено")
    @PositiveOrZero(message = "Проценты не могут быть отрицательными")
    private BigDecimal percent;

}
