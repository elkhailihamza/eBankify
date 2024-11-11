package org.project.Dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.Dto.AccountDto.AccountCreateDto;
import org.project.Dto.AccountDto.AccountViewDto;
import org.project.Entity.Account;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountCreateDto accountCreateDto);
    Account toAccount(AccountViewDto accountViewDto);

    AccountCreateDto getAccountToAccountCreateDto(Account account);
    AccountViewDto getAccountToAccountViewDto(Account account);
}
