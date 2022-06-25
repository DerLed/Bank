package ru.lebedev.bank.domain.client;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lebedev.bank.domain.EntityMapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Override
    @Mapping(target = "user", source = "userDTO")

    Client toEntity(ClientDTO dto);

    @Override
    @InheritInverseConfiguration
    @Mapping(target = "userDTO.password", ignore = true)
    ClientDTO toDTO(Client entity);
}
