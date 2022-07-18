package ru.lebedev.bank.domain.accountPlan.dto;

import lombok.*;
import ru.lebedev.bank.domain.helper.validGroup.Create;
import ru.lebedev.bank.domain.helper.validGroup.Update;


import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountPlanDTO {
    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank(message = "Поле тип не заполнено", groups = {Create.class, Update.class})
    private String title;

    @NotBlank(message = "Поле описание не заполнено", groups = {Create.class, Update.class})
    private String description;

    @NotNull(message = "Проценты не заполнено", groups = {Create.class, Update.class})
    @DecimalMax(value = "100.0", inclusive = false, message = "Проценты не могут больше 100%", groups = {Create.class, Update.class})
    @DecimalMin(value = "0", message = "Проценты не могут быть отрицательными", groups = {Create.class, Update.class})
    private BigDecimal percent;

    @NotNull(message = "Проценты не заполнено", groups = {Create.class, Update.class})
    @DecimalMin(value = "0", message = "Cумма не может быть отрицательной", groups = {Create.class, Update.class})
    private BigDecimal minAmount;

    @DecimalMin(value = "1", message = "Период должен быть от 1 месяца", groups = {Create.class, Update.class})
    private Long minPeriod;

}
