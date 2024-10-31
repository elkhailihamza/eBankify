package org.project.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.Enum.AccountStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
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
}
