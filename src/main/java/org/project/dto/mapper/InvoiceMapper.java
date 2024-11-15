package org.project.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.project.dto.request.InvoiceReqDto;
import org.project.dto.response.InvoiceResDto;
import org.project.entity.Invoice;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "amountDue", target = "amountDue")
    @Mapping(source = "dueDate", target = "dueDate")
    @Mapping(source = "owner", target = "owner")
    Invoice toInvoice(InvoiceReqDto invoiceReqDto);

    @Mapping(source = "amountDue", target = "amountDue")
    @Mapping(source = "dueDate", target = "dueDate")
    @Mapping(source = "owner", target = "owner")
    Invoice toInvoice(InvoiceResDto invoiceResDto);

    InvoiceReqDto getInvoiceToInvoiceReqDto(Invoice invoice);
    InvoiceResDto getInvoiceToInvoiceResDto(Invoice invoice);
}
