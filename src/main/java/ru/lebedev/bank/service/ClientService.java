package ru.lebedev.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.entity.Client;
import ru.lebedev.bank.domain.Status;
import ru.lebedev.bank.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;

    }

    public List<Client> all(){
        return clientRepository.findAll();
    }

    public Client save(Client client){
        client.getUser().setPassword(passwordEncoder.encode(client.getUser().getPassword()));
        client.getUser().setLogin(client.getPhoneNumber());
        client.getUser().setStatus(Status.ACTIVE);
        return clientRepository.save(client);
    }
}
