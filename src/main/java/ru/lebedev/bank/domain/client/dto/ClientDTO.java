package ru.lebedev.bank.domain.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.user.dto.UserDTO;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {

    private Long id;

    @Email
    @NotBlank(message = "Поле Email не может быть пустым")
    private String email;

    @NotBlank(message = "Поле номер телефона не может быть пустым")
    @Min(value = 6, message = "Номер телефона минимум 6 символов")
    private String phoneNumber;

    @Valid
    private UserDTO userDTO;
}
