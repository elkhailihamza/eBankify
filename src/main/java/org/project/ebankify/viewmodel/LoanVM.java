package org.project.ebankify.viewmodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.ebankify.dto.response.LoanResDto;

@Data
@NoArgsConstructor
public class LoanVM {
    private long id;
    private double principal;
    private double interestRate;
    private int termMonths;
    private long ownerId;

    public LoanVM(LoanResDto loanResDto) {
        this.id = loanResDto.getId();
        this.principal = loanResDto.getPrincipal();
        this.interestRate = loanResDto.getInterestRate();
        this.termMonths = loanResDto.getTermMonths();
        this.ownerId = loanResDto.getOwner().getId();
    }
}
