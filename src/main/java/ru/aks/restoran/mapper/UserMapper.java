package ru.aks.restoran.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.aks.restoran.dto.user.UserRequest;
import ru.aks.restoran.dto.user.UserResponse;
import ru.aks.restoran.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    UserResponse requestDto(User user);
    User toEntity(UserResponse userDto);
}