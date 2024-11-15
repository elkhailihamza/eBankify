package org.project.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.dto.request.LoanReqDto;
import org.project.dto.response.LoanResDto;
import org.project.entity.Loan;

@Mapper
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    Loan toLoan(LoanReqDto loanReqDto);
    Loan toLoan(LoanResDto loanResDto);

    LoanResDto getLoanToLoanResDto(Loan loan);
    LoanReqDto getLoanToLoanReqDto(Loan loan);
}
