package org.project.Controller;

import org.project.Entity.Loan;
import org.project.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    @Autowired
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan not found!");
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


}
