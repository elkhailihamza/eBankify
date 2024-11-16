package org.project.ebankify.service;

import org.project.ebankify.dao.TransactionDao;
import org.project.ebankify.dto.mapper.TransactionMapper;
import org.project.ebankify.dto.request.TransactionReqDto;
import org.project.ebankify.dto.response.TransactionResDto;
import org.project.ebankify.entity.Transaction;
import org.project.ebankify.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Transaction toTransaction(TransactionReqDto transactionReqDto) {
        return TransactionMapper.INSTANCE.toTransaction(transactionReqDto);
    }

    public TransactionResDto getTransactionToTransactionResDto(Transaction transaction) {
        return TransactionMapper.INSTANCE.getTransactionToTransactionResDto(transaction);
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

    public List<Transaction> getAllTransactionHistory() {
        return transactionDao.findAll();
    }
}
