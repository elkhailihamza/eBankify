package org.project.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.dto.request.AccountReqDto;
import org.project.dto.response.AccountResDto;
import org.project.entity.Account;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountReqDto accountReqDto);
    Account toAccount(AccountResDto accountResDto);

    AccountReqDto getAccountToAccountCreateDto(Account account);
    AccountResDto getAccountToAccountViewDto(Account account);
}
