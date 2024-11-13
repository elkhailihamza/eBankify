package org.project.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.project.Dto.request.TransactionReqDto;
import org.project.Dto.response.TransactionResDto;
import org.project.Entity.Account;
import org.project.Entity.Transaction;
import org.project.Entity.User;
import org.project.Enum.AccountStatus;
import org.project.Enum.TransactionStatus;
import org.project.Service.AccountService;
import org.project.Service.TransactionService;
import org.project.Service.UserService;
import org.project.viewmodel.TransactionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService, UserService userService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.userService = userService;
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

    @PostMapping("/{transactionId}/accept")
    public ResponseEntity<?> acceptTransaction(@PathVariable long transactionId) {
        Optional<Transaction> transactionOpt = transactionService.findTransactionById(transactionId);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            transaction.getSourceAccount().setBalance(transaction.getSourceAccount().getBalance() - transaction.getAmount());
            transaction.getDestinationAccount().setBalance(transaction.getDestinationAccount().getBalance() + transaction.getAmount());
            transaction.setStatus(TransactionStatus.ACCEPTED);
            transactionService.saveTransaction(transaction);
            return ResponseEntity.ok("Transaction accepted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found!");
    }

    @PostMapping("/{transactionId}/refuse")
    public ResponseEntity<?> refuseTransaction(@PathVariable long transactionId) {
        Optional<Transaction> transactionOpt = transactionService.findTransactionById(transactionId);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            transaction.setStatus(TransactionStatus.REFUSED);
            transactionService.saveTransaction(transaction);
            return ResponseEntity.ok("Transaction refused!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found!");
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserTransactionHistory(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("AUTH.id");
        Optional<User> userOpt = userService.findUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<Transaction> transactionHistory = transactionService.getUserTransactionHistory(user);
            if (!transactionHistory.isEmpty()) {
                List<TransactionViewModel> transactionVMList = transactionHistory.stream()
                        .map(transactionService::getTransactionToTransactionResDto)
                        .map(TransactionViewModel::new).toList();

                return ResponseEntity.ok(transactionVMList);
            }
            return ResponseEntity.ok("Transaction history is empty!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }
}
