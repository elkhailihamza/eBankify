package org.project.entity;

import jakarta.persistence.*;
import org.project.enums.Role;

import java.util.List;

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

    public User(Role role, int creditScore, double monthlyIncome, int age, String password, String email, String surname, String name, long id) {
        this.role = role;
        this.creditScore = creditScore;
        this.monthlyIncome = monthlyIncome;
        this.age = age;
        this.password = password;
        this.email = email;
        this.surname = surname;
        this.name = name;
        this.id = id;
    }

    public User() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
