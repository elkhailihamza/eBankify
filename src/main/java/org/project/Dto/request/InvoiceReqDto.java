package org.project.Dto.request;

import lombok.Data;
import org.project.Entity.User;

import java.time.LocalDateTime;

@Data
public class InvoiceReqDto {
    private String amountDue;
    private LocalDateTime dueDate;
    private User owner;
}
