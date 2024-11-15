package org.project.ebankify.entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.ebankify.type.Role;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
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

    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 2")
    private Role role = Role.USER;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

    @OneToMany(mappedBy = "owner")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "owner")
    private List<Loan> loans;
}
