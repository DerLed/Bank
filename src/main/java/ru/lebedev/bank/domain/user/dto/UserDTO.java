package ru.lebedev.bank.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.user.auth.Role;
import ru.lebedev.bank.domain.user.auth.Status;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    @NotBlank(message = "Name incorrect")
    private String name;

    @NotBlank(message = "Surname incorrect")
    private String surname;

    @NotBlank(message = "Login must be min 6 characters")
    @Min(value = 6)
    private String login;

    @NotBlank(message = "Password must be min 8 characters")
    @Min(value = 8)
    private String password;

    @NotNull(message = "Status must be filled in")
    private Status status;

    @NotNull(message = "Role must be filled in")
    private Role role;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime dateRegister;
}
