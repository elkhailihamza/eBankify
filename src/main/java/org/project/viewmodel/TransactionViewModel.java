package org.project.viewmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.project.Dto.response.TransactionResDto;

@Getter
@NoArgsConstructor
public class TransactionViewModel {
    private long id;
    private String type;
    private double amount;
    private String accountSrcNumber;
    private String accountDesNumber;
    private String status;

    public TransactionViewModel(TransactionResDto transactionResDto) {
        this.id= transactionResDto.getId();
        this.type = transactionResDto.getType().name();
        this.amount = transactionResDto.getAmount();
        this.accountSrcNumber = transactionResDto.getSourceAccount().getAccountNumber();
        this.accountDesNumber = transactionResDto.getDestinationAccount().getAccountNumber();
        this.status = transactionResDto.getStatus().name();
    }
}
