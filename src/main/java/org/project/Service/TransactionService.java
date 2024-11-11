package org.project.Service;

import org.project.Dao.TransactionDao;
import org.project.Entity.Account;
import org.project.Entity.Transaction;
import org.project.Entity.User;
import org.project.Enum.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Transaction createTransaction(Account source, Account destination, double amount, TransactionType transactionType) {
        Transaction transaction = Transaction.builder()
                .type(transactionType)
                .amount(amount)
                .sourceAccount(source)
                .destinationAccount(destination)
                .build();

        return transactionDao.save(transaction);
    }

    public List<Transaction> getTransactionHistory(User user) {
        return transactionDao.findBySourceAccount_Owner_Id(user.getId());
    }
}
