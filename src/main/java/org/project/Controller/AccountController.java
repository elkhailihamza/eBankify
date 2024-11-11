package org.project.Controller;

import org.project.Dto.AccountDto.AccountCreateDto;
import org.project.Dto.AccountDto.AccountViewDto;
import org.project.Entity.Account;
import org.project.Service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    @ResponseBody
    public AccountViewDto createNewAccount(@RequestBody AccountCreateDto accountCreateDto) {
        Account newAccount = accountService.toAccount(accountCreateDto);
        return accountService.createAccount(newAccount, 16);
    }
}
