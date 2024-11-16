package org.project.ebankify.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.ebankify.dto.request.authdto.LoginDto;
import org.project.ebankify.dto.request.authdto.RegisterDto;
import org.project.ebankify.dto.request.UserReqDto;
import org.project.ebankify.entity.User;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RegisterDto registerDto);
    User toUser(LoginDto loginDto);
    User toUser (UserReqDto userReqDto);
}
