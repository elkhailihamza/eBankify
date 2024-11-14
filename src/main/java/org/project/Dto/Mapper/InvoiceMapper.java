package org.project.Dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.project.Dto.request.InvoiceReqDto;
import org.project.Entity.Invoice;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "amountDue", target = "amountDue")
    @Mapping(source = "dueDate", target = "dueDate")
    @Mapping(source = "owner", target = "owner")
    Invoice toInvoice(InvoiceReqDto invoiceReqDto);

    InvoiceReqDto getInvoiceToInvoiceReqDto(Invoice invoice);
}
