package ru.kopnin.bankservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.kopnin.bankservice.DTO.ClientDTO;
import ru.kopnin.bankservice.models.postgres.Client;

@Mapper
public interface ClientMapper {
    Client toClient(ClientDTO clientDTO);
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
}
