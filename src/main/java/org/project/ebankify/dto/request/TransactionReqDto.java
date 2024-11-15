package org.project.ebankify.dto.request;

import lombok.Data;
import org.project.ebankify.entity.Account;
import org.project.ebankify.type.TransactionType;

@Data
public class TransactionReqDto {
    private TransactionType type;
    private double amount;
    private Account sourceAccount;
    private Account destinationAccount;
}
