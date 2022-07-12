package ru.lebedev.bank.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
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
    @Transactional
    public UserDTO updateById(Long id,UserDTO dto) {
        return userRepository.findById(id)
                .map(account -> {
                    BeanUtils.copyProperties(userMapper.toEntity(dto), account, "id");
                    return userMapper.toDTO(userRepository.save(account));
                })
                .orElseGet(() -> {
                    dto.setId(id);
                    return userMapper.toDTO(userRepository.save(userMapper.toEntity(dto)));
                });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO> findByLogin(String login) {
        return userRepository.findByLogin(login).map(userMapper::toDTO);
    }

    @Override
    public boolean checkIfUserExist(String login) {
        return userRepository.findByLogin(login).isPresent();
    }
}
