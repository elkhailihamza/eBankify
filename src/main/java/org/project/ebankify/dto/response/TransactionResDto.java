package org.project.ebankify.dto.response;

import lombok.Data;
import org.project.ebankify.entity.Account;
import org.project.ebankify.type.TransactionStatus;
import org.project.ebankify.type.TransactionType;

@Data
public class TransactionResDto {
    private long id;
    private TransactionType type;
    private double amount;
    private Account sourceAccount;
    private Account destinationAccount;
    private TransactionStatus status;
}
