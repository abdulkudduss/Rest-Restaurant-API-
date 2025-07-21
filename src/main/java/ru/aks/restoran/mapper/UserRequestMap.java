package ru.aks.restoran.mapper;

import org.mapstruct.Mapper;
import ru.aks.restoran.dto.user.UserRequest;
import ru.aks.restoran.entities.User;

@Mapper(componentModel = "spring")
public interface UserRequestMap {
    UserRequest userRequestDto(User user);
    User toEntity(UserRequest userRequestDto);
}
