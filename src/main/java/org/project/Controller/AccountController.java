package org.project.Controller;

import org.project.Dto.request.AccountReqDto;
import org.project.Entity.Account;
import org.project.Entity.User;
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
    public ResponseEntity<?> viewUserAccounts(@RequestBody AccountReqDto accountReqDto) {
        User owner = accountReqDto.getOwner();
        List<Account> userAccounts = accountService.fetchAllUserAccounts(owner);
        if (!userAccounts.isEmpty()) {
            List<AccountViewModel> accountViewModels = userAccounts.stream()
                    .map(accountService::getAccountToAccountResDto)
                    .map(AccountViewModel::new).toList();
            return ResponseEntity.ok(accountViewModels);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No accounts found!");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewAccount(@RequestBody AccountReqDto accountReqDto) {
        Optional<User> user = userService.findUserById(accountReqDto.getOwner().getId());
        if (user.isPresent()) {
            User existingUser = user.get();
            int creditScore = existingUser.getCreditScore();
            if (creditScore < 600) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Credit Score too low! \nNeeds to be 600 or more.");
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AccountViewModel(accountService.getAccountToAccountResDto(
                            accountService.createAccount(accountService.toAccount(accountReqDto), 16))));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Doesn't exist!");
    }
}
