package org.project.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.Enum.Role;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private int age;
    private double monthlyIncome = 0;
    private int creditScore = 0;
    private Role role = Role.USER;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

    @OneToMany(mappedBy = "owner")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "owner")
    private List<Loan> loans;
}
