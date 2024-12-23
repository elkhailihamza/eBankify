package org.project.ebankify.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.project.ebankify.dto.response.LoanResDto;
import org.project.ebankify.entity.Loan;
import org.project.ebankify.entity.User;
import org.project.ebankify.service.LoanService;
import org.project.ebankify.viewmodel.LoanVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/{loanId}/accept")
    public ResponseEntity<?> acceptLoan(@PathVariable long loanId) {
        Optional<Loan> loanOpt = loanService.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            loanService.acceptLoan(loan);
            return ResponseEntity.ok("Accepted loan!");
        }
        throw new EntityNotFoundException("Loan not found!");
    }

    @PostMapping("/{loanId}/refuse")
    public ResponseEntity<?> refuseLoan(@PathVariable long loanId) {
        Optional<Loan> loanOpt = loanService.findById(loanId);
        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            loanService.refuseLoan(loan);
            return ResponseEntity.ok("Refused loan!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found!");
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestLoan(@RequestBody LoanResDto loanResDto, HttpServletRequest request) {
        Long userId = (Long) request.getSession(false).getAttribute("AUTH.id");
        Loan loan = loanService.toLoan(loanResDto);
        loan.setOwner(User.builder().id(userId).build());
        loanService.saveLoan(loan);
        return ResponseEntity.ok("Loan created");
    }

    @GetMapping("/")
    public ResponseEntity<?> viewUserLoan(HttpServletRequest request) {
        Long userId = (Long) request.getSession(false).getAttribute("AUTH.id");
        List<Loan> loans = loanService.getUserLoanHistory(userId);
        List<LoanVM> loanVMs = loans.stream().map(loanService::getLoanToLoanResDto)
                .map(LoanVM::new)
                .toList();
        return ResponseEntity.ok(loanVMs);
    }

    @GetMapping("/all")
    public ResponseEntity<?> viewAllLoans() {
        List<Loan> loans = loanService.getLoanHistory();
        List<LoanVM> loanVMs = loans.stream().map(loanService::getLoanToLoanResDto)
                .map(LoanVM::new)
                .toList();
        return ResponseEntity.ok(loanVMs);
    }
}
