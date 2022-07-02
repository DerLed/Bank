package ru.lebedev.bank.domain.accountPlan;

import lombok.*;

import javax.persistence.Column;
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
    private String title;

    @NotBlank(message = "Поле описание не заполнено")
    private String description;

    @NotNull(message = "Проценты не заполнено")
    @PositiveOrZero(message = "Проценты не могут быть отрицательными")
    private BigDecimal percent;

    @NotNull(message = "Не выбран тип счета")
    private TypeAccount type;

}
