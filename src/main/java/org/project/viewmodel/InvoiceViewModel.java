package org.project.viewmodel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.Dto.request.InvoiceReqDto;
import org.project.Dto.response.InvoiceResDto;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class InvoiceViewModel {
    private long id;
    private String amountDue;
    private String dueDate;
    private long ownerId;

    public InvoiceViewModel(InvoiceResDto invoiceResDto) {
        this.id = invoiceResDto.getId();
        this.amountDue = invoiceResDto.getAmountDue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.dueDate = invoiceResDto.getDueDate().format(formatter);
        this.ownerId = invoiceResDto.getOwner().getId();
    }

    public InvoiceViewModel(InvoiceReqDto invoiceReqDto) {
    }
}
