package org.project.Dao;

import org.project.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanDao extends JpaRepository<Loan, Long> {
    List<Loan> findAllByOwner_Id(long ownerId);
}
