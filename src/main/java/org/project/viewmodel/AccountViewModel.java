package org.project.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.Dto.response.AccountResDto;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class AccountViewModel {
    private String accountNumber;
    private double balance;
    private String created_at;
    private String status;

    public AccountViewModel(AccountResDto accountResDto) {
        this.accountNumber = accountResDto.getAccountNumber();
        this.balance = accountResDto.getBalance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.created_at = accountResDto.getCreated_at().format(formatter);
        this.status = accountResDto.getStatus().name();
    }
}
