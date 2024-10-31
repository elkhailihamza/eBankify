package org.project.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double principal;
    private double interestRate;
    private int termMonths;

    @ManyToOne
    private User owner;
    private boolean approved;

    public Loan(long id, double principal, double interestRate, int termMonths, User owner, boolean approved) {
        this.id = id;
        this.principal = principal;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.owner = owner;
        this.approved = approved;
    }

    public Loan() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
