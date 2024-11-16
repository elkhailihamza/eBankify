package org.project.ebankify.service;

import org.project.ebankify.dao.AccountDao;
import org.project.ebankify.dto.request.AccountReqDto;
import org.project.ebankify.dto.response.AccountResDto;
import org.project.ebankify.dto.mapper.AccountMapper;
import org.project.ebankify.entity.Account;
import org.project.ebankify.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {
    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
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
