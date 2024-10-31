package org.project.Dao;

import org.project.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountDao extends JpaRepository<Account, UUID> {
    boolean existsAccountByAccountNumber(String accountNumber);
}
