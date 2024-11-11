package org.project.Service;

import org.project.Dao.AccountDao;
import org.project.Dao.UserDao;
import org.project.Dto.AccountDto.AccountCreateDto;
import org.project.Dto.AccountDto.AccountViewDto;
import org.project.Dto.Mapper.AccountMapper;
import org.project.Entity.Account;
import org.project.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountService {
    private final AccountDao accountDao;
    private final UserDao userDao;

    @Autowired
    public AccountService(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    public Account toAccount(AccountCreateDto accountCreateDto) {
        return AccountMapper.INSTANCE.toAccount(accountCreateDto);
    }

    public AccountCreateDto getAccountToAccountCreateDto(Account account) {
        return AccountMapper.INSTANCE.getAccountToAccountCreateDto(account);
    }

    public AccountViewDto getAccountToAccountViewDto(Account account) {
        return AccountMapper.INSTANCE.getAccountToAccountViewDto(account);
    }

    public AccountViewDto createAccount(Account newAccount, int accountNumLength) {
        String accountNumber = getUniqueAccountNum(accountNumLength);

        newAccount.setAccountNumber(accountNumber);

        newAccount = accountDao.save(newAccount);

        return getAccountToAccountViewDto(newAccount);
    }

    private String generateAccountNum(int length) {
        StringBuilder accountNum = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {
            int randomDigit = random.nextInt(10);
            accountNum.append(randomDigit);
        }

        return accountNum.toString();
    }

    private String getUniqueAccountNum(int length) {
        String accountNum;

        do {
            accountNum = generateAccountNum(length);
        } while(accountDao.existsAccountByAccountNumber(accountNum));

        return accountNum;
    }
}
