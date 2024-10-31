package org.project.Dao;

import org.project.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDao extends JpaRepository<Loan, Long> {
}
