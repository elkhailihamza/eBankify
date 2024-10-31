package org.project.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String amountDue;
    private Timestamp dueDate;

    @ManyToOne
    private User owner;

    public Invoice(long id, String amountDue, Timestamp dueDate, User owner) {
        this.id = id;
        this.amountDue = amountDue;
        this.dueDate = dueDate;
        this.owner = owner;
    }

    public Invoice() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
