package org.project.ebankify.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.project.ebankify.dto.request.TransactionReqDto;
import org.project.ebankify.entity.Account;
import org.project.ebankify.entity.Transaction;
import org.project.ebankify.entity.User;
import org.project.ebankify.exceptions.EntityCRUDFailedException;
import org.project.ebankify.exceptions.InvalidFundsException;
import org.project.ebankify.exceptions.UnexpectedErrorException;
import org.project.ebankify.type.AccountStatus;
import org.project.ebankify.type.TransactionStatus;
import org.project.ebankify.type.TransactionType;
import org.project.ebankify.service.AccountService;
import org.project.ebankify.service.TransactionService;
import org.project.ebankify.service.UserService;
import org.project.ebankify.viewmodel.TransactionVM;
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
        Long userId = (Long) request.getSession().getAttribute("AUTH.id");
        Transaction transaction = transactionService.toTransaction(transactionReqDto);
        Optional<Account> srcAccountOpt = accountService.fetchAccountByAccountNumber(transaction.getSourceAccount().getAccountNumber());
        Optional<Account> destAccountOpt = accountService.fetchAccountByAccountNumber(transaction.getDestinationAccount().getAccountNumber());

        if (srcAccountOpt.isEmpty() || destAccountOpt.isEmpty()) {
            throw new UnexpectedErrorException("An error has occurred!");
        }

        Account srcAccount = srcAccountOpt.get();
        Account destAccount = destAccountOpt.get();

        if (srcAccount.getStatus() == AccountStatus.BLOCKED || destAccount.getStatus() == AccountStatus.BLOCKED || srcAccount.getOwner().getId() != userId) {
            throw new EntityCRUDFailedException("Transaction creation failed!");

        }

        if (srcAccount.getBalance() < transaction.getAmount()) {
            throw new InvalidFundsException("Insufficient Funds!");
        }

        transaction.setStatus(transaction.getAmount() > 3000 ? TransactionStatus.PENDING : TransactionStatus.ACCEPTED);
        transaction.setType(TransactionType.INSTANT);
        transaction.setSourceAccount(srcAccount);
        transaction.setDestinationAccount(destAccount);

        transaction = transactionService.saveTransaction(transaction);
        if (transaction.getStatus() == TransactionStatus.ACCEPTED) {
            acceptTransaction(transaction.getId());
        }

        return ResponseEntity.ok("Transaction created!");
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
        throw new EntityNotFoundException("Transaction not found!");
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
        throw new EntityNotFoundException("Transaction not found!");
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserTransactionHistory(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("AUTH.id");
        Optional<User> userOpt = userService.findUserById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<Transaction> transactionHistory = transactionService.getUserTransactionHistory(user);
            List<TransactionVM> transactionVMList = transactionHistory.stream()
                    .map(transactionService::getTransactionToTransactionResDto)
                    .map(TransactionVM::new).toList();

            return ResponseEntity.ok(transactionVMList);
        }
        throw new EntityNotFoundException("User not found!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionVM>> getTransactionHistory() {
        List<Transaction> transactions = transactionService.getAllTransactionHistory();
        List<TransactionVM> transactionVMs = transactions.stream()
                .map(transactionService::getTransactionToTransactionResDto)
                .map(TransactionVM::new).toList();

        return ResponseEntity.ok(transactionVMs);
    }
}
