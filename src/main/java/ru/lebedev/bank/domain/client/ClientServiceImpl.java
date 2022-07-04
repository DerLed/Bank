package ru.lebedev.bank.domain.client;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.Role;
import ru.lebedev.bank.domain.Status;
import ru.lebedev.bank.domain.client.dto.ClientCreateReq;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.domain.client.mapper.ClientMapper;
import ru.lebedev.bank.domain.user.dto.UserDTO;
import ru.lebedev.bank.domain.user.UserService;
import ru.lebedev.bank.exception.UserAlreadyExistException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;


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

        if(client.getUser().getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(client.getUser().getPassword());
            client.getUser().setPassword(encodedPassword);
        }

        clientRepository.saveAndFlush(client);
        return clientMapper.toDTO(client);
    }

    @Override
    public ClientDTO newClient(ClientCreateReq clientCreateReq) throws UserAlreadyExistException {


        if(userService.checkIfUserExist(clientCreateReq.getPhoneNumber())){
            throw new UserAlreadyExistException("User already exists for this phone");
        }

        String encodedPassword = passwordEncoder.encode(clientCreateReq.getPassword());
        ClientDTO clientDTO = ClientDTO.builder()
                .userDTO(
                        UserDTO.builder()
                                .name(clientCreateReq.getName())
                                .surname(clientCreateReq.getSurname())
                                .login(clientCreateReq.getPhoneNumber())
                                .password(encodedPassword)
                                .status(Status.ACTIVE)
                                .role(Role.USER)
                                .build()
                )
                .email(clientCreateReq.getEmail())
                .phoneNumber(clientCreateReq.getPhoneNumber())
                .build();
        Client client = clientMapper.toEntity(clientDTO);
        clientRepository.saveAndFlush(client);
        return clientMapper.toDTO(client);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<ClientDTO> findByUserLogin(String login) {
        return clientRepository.findByUserLogin(login).map(clientMapper::toDTO);
    }
}
