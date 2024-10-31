package org.project.entity;

import jakarta.persistence.*;
import org.project.enums.TransactionStatus;
import org.project.enums.TransactionType;

import java.util.UUID;

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
    private TransactionStatus status;

    public Transaction(long id, TransactionType type, double amount, Account sourceAccountId, Account destinationAccountId, TransactionStatus status) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.status = status;
    }

    public Transaction() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Account sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Account getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(Account destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
