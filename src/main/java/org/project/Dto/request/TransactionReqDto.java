package org.project.Dto.request;

import lombok.Data;
import org.project.Entity.Account;
import org.project.Enum.TransactionType;

@Data
public class TransactionReqDto {
    private TransactionType type;
    private double amount;
    private Account sourceAccount;
    private Account destinationAccount;
}
