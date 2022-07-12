package ru.lebedev.bank.domain.user;

import ru.lebedev.bank.domain.helper.BaseService;
import ru.lebedev.bank.domain.user.dto.UserDTO;

import java.util.Optional;

public interface UserService extends BaseService<UserDTO, Long> {

    Optional<UserDTO> findByLogin(String login);

    boolean checkIfUserExist(String login);
}
