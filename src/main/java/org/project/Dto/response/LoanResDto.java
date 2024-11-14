package org.project.Dto.response;

import lombok.Data;
import org.project.Entity.User;

@Data
public class LoanResDto {
    private long id;
    private double principal;
    private double interestRate;
    private int termMonths;
    private User owner;
}
