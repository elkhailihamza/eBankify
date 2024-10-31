package org.project.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.Enum.AccountStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, unique = true)
    private String accountNumber;
    private double balance = 0;
    private LocalDateTime created_at = LocalDateTime.now();
    private AccountStatus status = AccountStatus.ACTIVE;

    @ManyToOne
    private User owner;
    @OneToMany(mappedBy = "sourceAccountId")
    private List<Transaction> sentTransactions;
    @OneToMany(mappedBy = "destinationAccountId")
    private List<Transaction> receivedTransactions;
}
