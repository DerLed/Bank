package ru.lebedev.bank.domain.client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDTO> findAll();
    Optional<ClientDTO> findById(Long id);
    Optional<ClientDTO> findByPhoneNumber(String phoneNumber);
    Optional<ClientDTO> findByEmail(String email);
    ClientDTO save(ClientDTO clientDTO);
    void deleteById(Long id);
}
