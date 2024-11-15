package org.project.ebankify.dto.response;

import lombok.Data;
import org.project.ebankify.entity.User;

import java.time.LocalDateTime;

@Data
public class InvoiceResDto {
    private long id;
    private String amountDue;
    private LocalDateTime dueDate;
    private User owner;
}
