package org.project.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.project.Enum.Role;

import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private double monthlyIncome;
    private int creditScore;
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

    @OneToMany(mappedBy = "owner")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "owner")
    private List<Loan> loans;
}
