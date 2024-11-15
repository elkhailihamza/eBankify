package org.project.ebankify.dto.request;

import lombok.Data;

@Data
public class LoanReqDto {
    private double principal;
    private double interestRate;
    private int termMonths;
}
