package ru.lebedev.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.entity.Client;
import ru.lebedev.bank.domain.entity.PersonStatus;
import ru.lebedev.bank.repository.UserRepo;

import java.util.List;

@Service
public class ClientService {

    private final UserRepo userRepo;

    @Autowired
    public ClientService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<Client> all(){
        return userRepo.findAll();
    }

    public Client save(Client client){
        client.getUser().setLogin(client.getPhone());
        client.getUser().setStatus(PersonStatus.ACTIVE);
        return userRepo.save(client);
    }
}
