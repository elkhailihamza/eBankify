package org.project.Controller;

import org.project.Dto.request.AccountResDto;
import org.project.Dto.response.AccountReqDto;
import org.project.Entity.Account;
import org.project.Entity.User;
import org.project.Service.AccountService;
import org.project.Service.UserService;
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
    public ResponseEntity<?> viewUserAccounts(@RequestBody AccountResDto accountResDto) {
        User owner = accountResDto.getOwner();
        List<Account> userAccounts = accountService.fetchAllUserAccounts(owner);
        if (!userAccounts.isEmpty()) {
            return null;
        }
        return null;
    }

    @PostMapping("/create")
    @ResponseBody
    public String createNewAccount(@RequestBody AccountResDto accountResDto) {
        Optional<User> user = userService.findUserById(accountResDto.getOwner().getId());
        if (user.isPresent()) {
            User existingUser = user.get();
            int creditScore = existingUser.getCreditScore();
            if (creditScore < 600) {
                return "Credit Score too low! \nNeeds to be 600 or more.";
            }

            Account newAccount = accountService.toAccount(accountResDto);
            AccountReqDto accountViewDto = accountService.createAccount(newAccount, 16);
            return accountViewDto.toString();
        }
        return "User Doesn't exist!";
    }
}
