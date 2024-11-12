package org.project.Dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.Dto.request.AccountReqDto;
import org.project.Dto.response.AccountResDto;
import org.project.Entity.Account;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountReqDto accountReqDto);
    Account toAccount(AccountResDto accountResDto);

    AccountReqDto getAccountToAccountCreateDto(Account account);
    AccountResDto getAccountToAccountViewDto(Account account);
}
