package org.project.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.project.Dto.request.TransactionReqDto;
import org.project.Entity.Account;
import org.project.Entity.Transaction;
import org.project.Enum.AccountStatus;
import org.project.Enum.TransactionStatus;
import org.project.Service.AccountService;
import org.project.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewTransaction(@RequestBody TransactionReqDto transactionReqDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("AUTH.id");
        Transaction transaction = transactionService.toTransaction(transactionReqDto);
        Account srcAccount = transaction.getSourceAccount();
        Account destAccount = transaction.getDestinationAccount();

        Optional<Account> srcAccountOpt = accountService.fetchAccountByAccountNumber(srcAccount.getAccountNumber());
        Optional<Account> destAccountOpt = accountService.fetchAccountByAccountNumber(destAccount.getAccountNumber());

        if (srcAccountOpt.isPresent() && destAccountOpt.isPresent()) {
            srcAccount = srcAccountOpt.get();
            destAccount = destAccountOpt.get();

            if (srcAccount.getStatus() == AccountStatus.BLOCKED || destAccount.getStatus() == AccountStatus.BLOCKED || srcAccount.getOwner().getId() != userId) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Transaction creation failed!");
            }

            if (srcAccount.getBalance() >= transaction.getAmount()) {
                transaction.setStatus(TransactionStatus.PENDING);
                transaction.setSourceAccount(srcAccount);
                transaction.setDestinationAccount(destAccount);
                transactionService.saveTransaction(transaction);
                return ResponseEntity.ok("Transaction created!");
            } else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Insufficient Funds!");
            }
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("An error has occurred!");
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserTransactionHistory() {
        return null;
    }
}
