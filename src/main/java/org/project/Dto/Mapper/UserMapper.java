package org.project.Dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.Dto.request.AuthDto.LoginDto;
import org.project.Dto.request.AuthDto.RegisterDto;
import org.project.Dto.request.UserReqDto;
import org.project.Entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RegisterDto registerDto);
    User toUser(LoginDto loginDto);
    User toUser (UserReqDto userReqDto);

    LoginDto userToLoginDto(User user);
    RegisterDto userToRegisterDto(User user);
}
