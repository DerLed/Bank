package ru.lebedev.bank.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.helper.validGroup.Create;
import ru.lebedev.bank.domain.helper.validGroup.Update;
import ru.lebedev.bank.domain.user.auth.Role;
import ru.lebedev.bank.domain.user.auth.Status;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @NotNull(groups = Update.class)
    @Null(groups = Create.class)
    private Long id;

    @NotBlank(message = "Name incorrect", groups = {Update.class, Create.class})
    private String name;

    @NotBlank(message = "Surname incorrect", groups = {Update.class, Create.class})
    private String surname;

    @NotBlank(message = "Login must be min 6 characters", groups = {Update.class, Create.class})
    @Min(value = 6, groups = {Update.class, Create.class})
    private String login;

    @NotBlank(message = "Password must be min 8 characters", groups = {Update.class, Create.class})
    @Min(value = 8, groups = {Update.class, Create.class})
    private String password;

    @NotNull(message = "Status must be filled in", groups = {Update.class, Create.class})
    private Status status;

    @NotNull(message = "Role must be filled in", groups = {Update.class, Create.class})
    private Role role;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime dateRegister;
}
