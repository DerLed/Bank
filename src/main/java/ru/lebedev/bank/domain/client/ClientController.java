package ru.lebedev.bank.domain.client;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.exception.ClientNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> findAll() {
        return clientService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return clientService.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
    }

    @PostMapping
    public ClientDTO create(@RequestBody @Valid ClientDTO client) {
        return clientService.save(client);
    }

    @PutMapping
    public ClientDTO update(@RequestBody @Valid ClientDTO client) {
        return clientService.save(client);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
