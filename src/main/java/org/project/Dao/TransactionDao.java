package org.project.Dao;

import org.project.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {
}
