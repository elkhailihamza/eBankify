package org.project.ebankify.dto.response;

import lombok.Data;
import org.project.ebankify.entity.User;

@Data
public class LoanResDto {
    private long id;
    private double principal;
    private double interestRate;
    private int termMonths;
    private User owner;
}
