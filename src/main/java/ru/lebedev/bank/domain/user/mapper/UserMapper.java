package ru.lebedev.bank.domain.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lebedev.bank.domain.helper.EntityMapper;
import ru.lebedev.bank.domain.user.User;
import ru.lebedev.bank.domain.user.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Override
    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User entity);
}
