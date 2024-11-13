package org.project.Dao;

import org.project.Dto.response.TransactionResDto;
import org.project.Entity.Transaction;
import org.project.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceAccount_Owner_Id(long userId);

    @Query("SELECT t FROM Transaction t WHERE t.sourceAccount.owner = :owner OR t.destinationAccount.owner = :owner")
    List<Transaction> findUserTransactionHistory(User owner);
}
