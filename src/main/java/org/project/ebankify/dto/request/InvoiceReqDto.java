package org.project.ebankify.dto.request;

import lombok.Data;
import org.project.ebankify.entity.User;

import java.time.LocalDateTime;

@Data
public class InvoiceReqDto {
    private String amountDue;
    private LocalDateTime dueDate;
    private User owner;
}
