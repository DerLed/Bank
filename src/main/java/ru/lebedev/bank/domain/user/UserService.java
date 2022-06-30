package ru.lebedev.bank.domain.user;

import ru.lebedev.bank.domain.client.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();
    Optional<UserDTO> findById(Long id);
    UserDTO save(UserDTO userDTO);
    void deleteById(Long id);
    Optional<UserDTO> findByLogin(String login);
    boolean checkIfUserExist(String login);
}
