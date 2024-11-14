package org.project.Service;

import org.project.Dao.LoanDao;
import org.project.Entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanDao loanDao;

    @Autowired
    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    public List<Loan> getLoanHistory() {
        return loanDao.findAll();
    }

    public List<Loan> getUserLoanHistory(long id) {
        return loanDao.findAllByOwner_Id(id);
    }

    public Loan acceptLoan(Loan loan) {
        loan.setApproved(true);
        return loanDao.save(loan);
    }

    public Loan refuseLoan(Loan loan) {
        loan.setApproved(false);
        return loanDao.save(loan);
    }
}
