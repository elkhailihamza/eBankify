package org.project.Service;

import org.project.Dao.AccountDao;
import org.project.Dao.UserDao;
import org.project.Dto.request.AccountReqDto;
import org.project.Dto.response.AccountResDto;
import org.project.Dto.Mapper.AccountMapper;
import org.project.Entity.Account;
import org.project.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Account toAccount(AccountReqDto accountReqDto) {
        return AccountMapper.INSTANCE.toAccount(accountReqDto);
    }

    public AccountReqDto getAccountToAccountReqDto(Account account) {
        return AccountMapper.INSTANCE.getAccountToAccountCreateDto(account);
    }

    public AccountResDto getAccountToAccountResDto(Account account) {
        return AccountMapper.INSTANCE.getAccountToAccountViewDto(account);
    }

    public AccountResDto createAccount(Account newAccount, int accountNumLength) {
        String accountNumber = getUniqueAccountNum(accountNumLength);

        newAccount.setAccountNumber(accountNumber);

        newAccount = accountDao.save(newAccount);

        return getAccountToAccountResDto(newAccount);
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

    public List<Account> fetchAllUserAccounts(User owner) {
        return accountDao.findAccountsByOwner(owner);
    }
}
