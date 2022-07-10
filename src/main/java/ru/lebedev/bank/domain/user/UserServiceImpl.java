package ru.lebedev.bank.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lebedev.bank.domain.user.auth.Role;
import ru.lebedev.bank.domain.user.auth.Status;
import ru.lebedev.bank.domain.user.dto.UserDTO;
import ru.lebedev.bank.domain.user.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        userRepository.saveAndFlush(user);
        return userMapper.toDTO(user);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<UserDTO> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public boolean checkIfUserExist(String login) {
        return userRepository.findByLogin(login).isPresent();
    }
}
