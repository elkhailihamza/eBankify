package org.project.Dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.Dto.request.AccountResDto;
import org.project.Dto.response.AccountReqDto;
import org.project.Entity.Account;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountResDto accountResDto);
    Account toAccount(AccountReqDto accountReqDto);

    AccountResDto getAccountToAccountCreateDto(Account account);
    AccountReqDto getAccountToAccountViewDto(Account account);
}
