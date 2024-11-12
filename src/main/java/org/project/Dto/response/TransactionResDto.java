package org.project.Dto.response;

import lombok.Data;
import org.project.Entity.Account;
import org.project.Enum.TransactionStatus;
import org.project.Enum.TransactionType;

@Data
public class TransactionResDto {
    private long id;
    private TransactionType type;
    private double amount;
    private Account sourceAccount;
    private Account destinationAccount;
    private TransactionStatus status;
}
