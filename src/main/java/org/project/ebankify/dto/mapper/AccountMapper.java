package org.project.ebankify.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.ebankify.dto.request.AccountReqDto;
import org.project.ebankify.dto.response.AccountResDto;
import org.project.ebankify.entity.Account;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountResDto getAccountToAccountViewDto(Account account);
}
