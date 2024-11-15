package org.project.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.dto.request.TransactionReqDto;
import org.project.dto.response.TransactionResDto;
import org.project.entity.Transaction;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toTransaction(TransactionResDto transactionResDto);
    Transaction toTransaction(TransactionReqDto transactionReqDto);

    TransactionResDto getTransactionToTransactionResDto(Transaction transaction);
    TransactionReqDto getTransactionToTransactionReqDto(Transaction transaction);
}
