package org.project.Dao;

import org.project.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceAccount_Owner_Id(long userId);
}
