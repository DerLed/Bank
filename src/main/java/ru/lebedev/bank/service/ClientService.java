package ru.lebedev.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.entity.Client;
import ru.lebedev.bank.domain.entity.PersonStatus;
import ru.lebedev.bank.repository.ClientRepo;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public List<Client> all(){
        return clientRepo.findAll();
    }

    public Client save(Client client){
        client.getPerson().setLogin(client.getPhone());
        client.getPerson().setStatus(PersonStatus.ACTIVE);
        return clientRepo.save(client);
    }
}
