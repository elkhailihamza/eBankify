package org.project.dto.request;

import lombok.Data;
import org.project.entity.User;

import java.time.LocalDateTime;

@Data
public class InvoiceReqDto {
    private String amountDue;
    private LocalDateTime dueDate;
    private User owner;
}
