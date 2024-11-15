package org.project.dto.request;

import lombok.Data;
import org.project.entity.Account;
import org.project.type.TransactionType;

@Data
public class TransactionReqDto {
    private TransactionType type;
    private double amount;
    private Account sourceAccount;
    private Account destinationAccount;
}
