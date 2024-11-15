package org.project.dto.response;

import lombok.Data;
import org.project.entity.Account;
import org.project.type.TransactionStatus;
import org.project.type.TransactionType;

@Data
public class TransactionResDto {
    private long id;
    private TransactionType type;
    private double amount;
    private Account sourceAccount;
    private Account destinationAccount;
    private TransactionStatus status;
}
