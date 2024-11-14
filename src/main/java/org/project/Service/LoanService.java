package org.project.Service;

import org.project.Dao.LoanDao;
import org.project.Dto.Mapper.LoanMapper;
import org.project.Dto.request.LoanReqDto;
import org.project.Dto.response.LoanResDto;
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

    public Loan toLoan(LoanReqDto loanReqDto) {
        return LoanMapper.INSTANCE.toLoan(loanReqDto);
    }

    public Loan toLoan(LoanResDto loanResDto) {
        return LoanMapper.INSTANCE.toLoan(loanResDto);
    }

    public LoanResDto getLoanToLoanResDto(Loan loan) {
        return LoanMapper.INSTANCE.getLoanToLoanResDto(loan);
    }

    public LoanReqDto getLoanToLoanReqDto(Loan loan) {
        return LoanMapper.INSTANCE.getLoanToLoanReqDto(loan);
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
