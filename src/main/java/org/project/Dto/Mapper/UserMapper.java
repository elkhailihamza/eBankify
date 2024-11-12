package org.project.Dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.Dto.request.AuthDto.LoginDto;
import org.project.Dto.request.AuthDto.RegisterDto;
import org.project.Entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    LoginDto userToLoginDto(User user);
    User loginDtoToUser(LoginDto loginDto);

    RegisterDto userToRegisterDto(User user);
    User registerDtoToUser(RegisterDto registerDto);
}
