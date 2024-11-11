package org.project.Controller;

import org.project.Entity.Transaction;
import org.project.Entity.User;
import org.project.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final TransactionService transactionService;

    @Autowired
    public UserController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction/history")
    public List<Transaction> getTransactionHistory() {
        User user = User.builder().id(1).build();
        return transactionService.getTransactionHistory(user);
    }
}
