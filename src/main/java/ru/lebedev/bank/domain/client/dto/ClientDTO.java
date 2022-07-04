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
    @NotBlank(message = "Email not be empty")
    private String email;

    @NotBlank(message = "Phone Number not be empty")
    @Min(value = 6, message = "Phone Number must be min 6 characters")
    private String phoneNumber;

    @Valid
    private UserDTO userDTO;
}
