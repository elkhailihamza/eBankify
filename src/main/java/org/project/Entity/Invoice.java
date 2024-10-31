package org.project.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String amountDue;
    private Timestamp dueDate;

    @ManyToOne
    private User owner;
}
