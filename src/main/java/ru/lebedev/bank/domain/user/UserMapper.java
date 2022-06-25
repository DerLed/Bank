package ru.lebedev.bank.domain.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lebedev.bank.domain.EntityMapper;
import ru.lebedev.bank.domain.client.Client;
import ru.lebedev.bank.domain.client.ClientDTO;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Override
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User entity);
}
