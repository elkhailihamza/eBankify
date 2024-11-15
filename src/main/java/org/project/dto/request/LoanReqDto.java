package org.project.dto.request;

import lombok.Data;

@Data
public class LoanReqDto {
    private double principal;
    private double interestRate;
    private int termMonths;
}
