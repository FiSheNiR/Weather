package ru.rybaltovskij.weather.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.rybaltovskij.weather.dto.UserRegisterDto;
import ru.rybaltovskij.weather.dto.UserSignInRequestDto;
import ru.rybaltovskij.weather.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "username", target = "login")
    User toEntity(UserRegisterDto userRegisterDto);
    UserRegisterDto toUserRegisterDto(User user);
}
