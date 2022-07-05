package ru.lebedev.bank.domain.accountPlan.dto;

import lombok.*;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountPlanDTO {
    private Long id;

    @NotBlank(message = "Поле тип не заполнено")
    private String title;

    @NotBlank(message = "Поле описание не заполнено")
    private String description;

    @NotNull(message = "Проценты не заполнено")
    @DecimalMax(value = "100.0", inclusive = false, message = "Проценты не могут больше 100%")
    @DecimalMin(value = "0", message = "Проценты не могут быть отрицательными")
    private BigDecimal percent;

    @NotNull(message = "Не выбран тип счета")
    private TypeAccount type;

}
