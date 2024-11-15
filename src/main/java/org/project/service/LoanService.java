package org.project.service;

import org.project.dao.LoanDao;
import org.project.dto.mapper.LoanMapper;
import org.project.dto.request.LoanReqDto;
import org.project.dto.response.LoanResDto;
import org.project.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return saveLoan(loan);
    }

    public Loan refuseLoan(Loan loan) {
        loan.setApproved(false);
        return saveLoan(loan);
    }

    public Optional<Loan> findById(long id) {
        return loanDao.findById(id);
    }

    public Loan saveLoan(Loan loan) {
        return loanDao.save(loan);
    }
}
