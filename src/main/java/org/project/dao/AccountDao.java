package org.project.dao;

import org.project.entity.Account;
import org.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountDao extends JpaRepository<Account, UUID> {
    boolean existsAccountByAccountNumber(String accountNumber);
    List<Account> findAccountsByOwner(User owner);
    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
