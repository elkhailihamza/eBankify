package org.project.Service;

import org.project.Dao.AccountDao;
import org.project.Dao.UserDao;
import org.project.Entity.Account;
import org.project.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public Account createNewAccount(long user_id) {
        Optional<User> user = userDao.findById(user_id);
        if (user.isPresent()) {
           String accountNumber = getUniqueAccountNum(17);

            Account account = Account.builder()
                    .accountNumber(accountNumber)
                    .owner(user.get())
                    .build();

            return accountDao.save(account);
        }

        return null;
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
