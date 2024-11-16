package org.project.ebankify.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.ebankify.dto.request.LoanReqDto;
import org.project.ebankify.dto.response.LoanResDto;
import org.project.ebankify.entity.Loan;

@Mapper
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    Loan toLoan(LoanResDto loanResDto);

    LoanResDto getLoanToLoanResDto(Loan loan);
}
