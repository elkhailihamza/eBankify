package org.project.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.dto.request.authdto.LoginDto;
import org.project.dto.request.authdto.RegisterDto;
import org.project.dto.request.UserReqDto;
import org.project.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RegisterDto registerDto);
    User toUser(LoginDto loginDto);
    User toUser (UserReqDto userReqDto);

    LoginDto userToLoginDto(User user);
    RegisterDto userToRegisterDto(User user);
}
