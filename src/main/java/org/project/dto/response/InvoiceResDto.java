package org.project.dto.response;

import lombok.Data;
import org.project.entity.User;

import java.time.LocalDateTime;

@Data
public class InvoiceResDto {
    private long id;
    private String amountDue;
    private LocalDateTime dueDate;
    private User owner;
}
