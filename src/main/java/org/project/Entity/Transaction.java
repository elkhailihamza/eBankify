package org.project.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.project.Enum.TransactionStatus;
import org.project.Enum.TransactionType;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private TransactionType type;
    private double amount;

    @ManyToOne
    private Account sourceAccountId;
    @ManyToOne
    private Account destinationAccountId;
    private TransactionStatus status = TransactionStatus.PENDING;
}
