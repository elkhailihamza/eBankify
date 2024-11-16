package org.project.ebankify.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.project.ebankify.entity.Account;
import org.project.ebankify.type.TransactionType;

@Data
public class TransactionReqDto {

    @NotNull(message = "Transaction type is required")
    private TransactionType type;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private double amount;

    @NotNull(message = "Source account is required")
    private Account sourceAccount;

    @NotNull(message = "Destination account is required")
    private Account destinationAccount;
}
