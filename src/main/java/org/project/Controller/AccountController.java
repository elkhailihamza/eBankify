package org.project.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.project.Dto.response.AccountResDto;
import org.project.Entity.Account;
import org.project.Entity.User;
import org.project.Enum.AccountStatus;
import org.project.Service.AccountService;
import org.project.Service.UserService;
import org.project.viewmodel.AccountViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<?> viewUserAccounts(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("AUTH.id");
        Optional<User> owner = userService.findUserById(userId);
        if (owner.isPresent()) {
            List<Account> userAccounts = accountService.fetchAllUserAccounts(owner.get());
            if (!userAccounts.isEmpty()) {
                List<AccountViewModel> accountViewModels = userAccounts.stream()
                        .map(accountService::getAccountToAccountResDto)
                        .map(AccountViewModel::new).toList();
                return ResponseEntity.ok(accountViewModels);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No accounts found!");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewAccount(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("AUTH.id");
        Optional<User> user = userService.findUserById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            int creditScore = existingUser.getCreditScore();
            if (creditScore < 600) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Credit Score too low! \nNeeds to be 600 or more.");
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AccountViewModel(accountService.getAccountToAccountResDto(
                            accountService.createAccount(new Account(), 16))));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Doesn't exist!");
    }

    @PostMapping("/{accountNumber}/disable")
    public ResponseEntity<?> disableAccount(@PathVariable String accountNumber) {
        Optional<Account> account = accountService.fetchAccountByAccountNumber(accountNumber);
        if (account.isPresent()) {
            Account foundAccount = account.get();
            foundAccount.setStatus(AccountStatus.BLOCKED);
            accountService.saveAccount(foundAccount);

            return ResponseEntity.ok("Account - "+accountNumber+" Blocked!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account specified not found!");
    }

    @GetMapping("/{accountNumber}/view")
    public ResponseEntity<?> viewCertainAccount(@PathVariable String accountNumber) {
        Optional<Account> account = accountService.fetchAccountByAccountNumber(accountNumber);
        if (account.isPresent()) {
            AccountResDto accountResDto = accountService.getAccountToAccountResDto(account.get());
            return ResponseEntity.ok(new AccountViewModel(accountResDto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found!");
    }

    @PostMapping("/{accountNumber}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber) {
        Optional<Account> account = accountService.fetchAccountByAccountNumber(accountNumber);
        if (account.isPresent()) {
            accountService.deleteAccount(account.get());
            return ResponseEntity.ok("Successfully deleted account!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found!");
    }
}
