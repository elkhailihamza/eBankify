package org.project.entity;

import jakarta.persistence.*;
import org.project.enums.AccountStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String accountNumber;
    private double balance;
    private Timestamp created_at;
    private AccountStatus status;

    @ManyToOne
    private User owner;
    @OneToMany(mappedBy = "sourceAccountId")
    private List<Transaction> sentTransactions;
    @OneToMany(mappedBy = "destinationAccountId")
    private List<Transaction> receivedTransactions;

    public Account(UUID id, String accountNumber, double balance, Timestamp created_at, AccountStatus status, User owner) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.created_at = created_at;
        this.status = status;
        this.owner = owner;
    }

    public Account() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }
}
