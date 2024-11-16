package org.project.ebankify.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.project.ebankify.dto.request.TransactionReqDto;
import org.project.ebankify.dto.response.TransactionResDto;
import org.project.ebankify.entity.Transaction;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toTransaction(TransactionReqDto transactionReqDto);

    TransactionResDto getTransactionToTransactionResDto(Transaction transaction);
}
