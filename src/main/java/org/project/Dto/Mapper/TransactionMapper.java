package org.project.Dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.Dto.request.TransactionReqDto;
import org.project.Dto.response.TransactionResDto;
import org.project.Entity.Transaction;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toTransaction(TransactionResDto transactionResDto);
    Transaction toTransaction(TransactionReqDto transactionReqDto);

    TransactionResDto getTransactionToTransactionResDto(Transaction transaction);
    TransactionReqDto getTransactionToTransactionReqDto(Transaction transaction);
}
