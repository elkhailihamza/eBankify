package org.project.ebankify.dto.response;

import lombok.Data;
import org.project.ebankify.type.AccountStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccountResDto {
    private UUID id;
    private String accountNumber;
    private double balance;
    private LocalDateTime created_at;
    private AccountStatus status;
}
