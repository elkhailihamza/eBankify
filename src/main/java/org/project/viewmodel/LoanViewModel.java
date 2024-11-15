package org.project.viewmodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.Dto.response.LoanResDto;

@Data
@NoArgsConstructor
public class LoanViewModel {
    private long id;
    private double principal;
    private double interestRate;
    private int termMonths;
    private long ownerId;

    public LoanViewModel(LoanResDto loanResDto) {
        this.id = loanResDto.getId();
        this.principal = loanResDto.getPrincipal();
        this.interestRate = loanResDto.getInterestRate();
        this.termMonths = loanResDto.getTermMonths();
        this.ownerId = loanResDto.getOwner().getId();
    }
}
