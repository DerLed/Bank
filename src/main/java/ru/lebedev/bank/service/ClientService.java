package ru.lebedev.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.entity.Client;
import ru.lebedev.bank.domain.Status;
import ru.lebedev.bank.repository.ClientRepo;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public ClientService(ClientRepo clientRepo, PasswordEncoder passwordEncoder) {
        this.clientRepo = clientRepo;

    }

    public List<Client> all(){
        return clientRepo.findAll();
    }

    public Client save(Client client){
        client.getUser().setPassword(passwordEncoder.encode(client.getUser().getPassword()));
        client.getUser().setLogin(client.getPhone());
        client.getUser().setStatus(Status.ACTIVE);
        return clientRepo.save(client);
    }
}
