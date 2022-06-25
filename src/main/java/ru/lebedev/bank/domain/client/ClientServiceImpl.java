package ru.lebedev.bank.domain.client;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.Role;
import ru.lebedev.bank.domain.Status;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;


    public List<ClientDTO> findAll(){
        return clientRepository.findAll()
                .stream().map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDTO> findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDTO);
    }

    @Override
    public Optional<ClientDTO> findByPhoneNumber(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber)
                .map(clientMapper::toDTO);
    }

    @Override
    public Optional<ClientDTO> findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .map(clientMapper::toDTO);
    }

    public ClientDTO save(ClientDTO clientDTO){
        Client client = clientMapper.toEntity(clientDTO);
        String encodedPassword = passwordEncoder.encode(client.getUser().getPassword());
        client.getUser().setPassword(encodedPassword);
        client.getUser().setLogin(client.getPhoneNumber());
        client.getUser().setRole(Role.USER);
        client.getUser().setStatus(Status.ACTIVE);
        clientRepository.saveAndFlush(client);
        return clientMapper.toDTO(client);
    }

    @Override
    public void deleteById(Long id) {

    }
}
