package ru.lebedev.bank.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ru.lebedev.bank.domain.Role;
import ru.lebedev.bank.domain.Status;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    @NotBlank(message = "Name incorrect")
    @Min(value = 2)
    private String name;

    @NotBlank(message = "Surname incorrect")
    @Min(value = 2)
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
}
