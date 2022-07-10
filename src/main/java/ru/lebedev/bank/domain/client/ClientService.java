package ru.lebedev.bank.domain.client;

import ru.lebedev.bank.domain.client.dto.ClientCreateReq;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.exception.UserAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientDTO> findAll();

    Optional<ClientDTO> findById(Long id);

    Optional<ClientDTO> findByPhoneNumber(String phoneNumber);

    Optional<ClientDTO> findByEmail(String email);

    ClientDTO save(ClientDTO clientDTO);

    ClientDTO newClient(ClientCreateReq clientCreate) throws UserAlreadyExistException;

    void deleteById(Long id);

    Optional<ClientDTO> findByUserLogin(String login);
}
