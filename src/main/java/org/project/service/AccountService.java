package org.project.service;

import org.project.dao.AccountDao;
import org.project.dto.request.AccountReqDto;
import org.project.dto.response.AccountResDto;
import org.project.dto.mapper.AccountMapper;
import org.project.entity.Account;
import org.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {
    private final AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
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

    public void saveAccount(Account account) {
        accountDao.save(account);
    }

    public void deleteAccount(Account account) {
        accountDao.delete(account);
    }

    public Account createAccount(Account newAccount, int accountNumLength) {
        String accountNumber = getUniqueAccountNum(accountNumLength);

        newAccount.setAccountNumber(accountNumber);

        return accountDao.save(newAccount);
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
        } while(accountExistsByAccountNumber(accountNum));

        return accountNum;
    }

    public List<Account> fetchAllUserAccounts(User owner) {
        return accountDao.findAccountsByOwner(owner);
    }

    public boolean accountExistsByAccountNumber(String accountNumber) {
        return accountDao.existsAccountByAccountNumber(accountNumber);
    }

    public Optional<Account> fetchAccountByAccountNumber(String accountNumber) {
        return accountDao.findAccountByAccountNumber(accountNumber);
    }
}
