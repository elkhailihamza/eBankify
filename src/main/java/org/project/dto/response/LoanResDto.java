package org.project.dto.response;

import lombok.Data;
import org.project.entity.User;

@Data
public class LoanResDto {
    private long id;
    private double principal;
    private double interestRate;
    private int termMonths;
    private User owner;
}
