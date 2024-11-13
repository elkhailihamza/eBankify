package org.project.Service;

import org.project.Dao.TransactionDao;
import org.project.Dto.Mapper.TransactionMapper;
import org.project.Dto.request.TransactionReqDto;
import org.project.Dto.response.TransactionResDto;
import org.project.Entity.Transaction;
import org.project.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Transaction toTransaction(TransactionResDto transactionResDto) {
        return TransactionMapper.INSTANCE.toTransaction(transactionResDto);
    }

    public Transaction toTransaction(TransactionReqDto transactionReqDto) {
        return TransactionMapper.INSTANCE.toTransaction(transactionReqDto);
    }

    public TransactionResDto getTransactionToTransactionResDto(Transaction transaction) {
        return TransactionMapper.INSTANCE.getTransactionToTransactionResDto(transaction);
    }

    public TransactionReqDto getTransactionToTransactionReqDto(Transaction transaction) {
        return TransactionMapper.INSTANCE.getTransactionToTransactionReqDto(transaction);
    }

    public Optional<Transaction> findTransactionById(long id) {
        return transactionDao.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public List<Transaction> getUserTransactionHistory(User user) {
        return transactionDao.findUserTransactionHistory(user);
    }
}
