package org.project.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String amountDue;
    private LocalDateTime dueDate = LocalDateTime.now().plusDays(3);

    @PrePersist
    public void prePersist() {
        if (dueDate == null) {
            dueDate = LocalDateTime.now().plusDays(3);
        }
    }

    @ManyToOne
    private User owner;
}
