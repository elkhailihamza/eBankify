package org.project.Dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.Dto.request.LoanReqDto;
import org.project.Dto.response.LoanResDto;
import org.project.Entity.Loan;

@Mapper
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    Loan toLoan(LoanReqDto loanReqDto);
    Loan toLoan(LoanResDto loanResDto);

    LoanResDto getLoanToLoanResDto(Loan loan);
    LoanReqDto getLoanToLoanReqDto(Loan loan);
}
