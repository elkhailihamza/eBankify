package org.project.Service;

import org.project.Dao.InvoiceDao;
import org.project.Dto.Mapper.InvoiceMapper;
import org.project.Dto.request.InvoiceReqDto;
import org.project.Entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceDao invoiceDao;

    @Autowired
    public InvoiceService(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public Optional<Invoice> getInvoiceById(long id) {
        return invoiceDao.findById(id);
    }

    public Invoice toInvoice(InvoiceReqDto invoiceReqDto) {
        return InvoiceMapper.INSTANCE.toInvoice(invoiceReqDto);
    }

    public InvoiceReqDto getInvoiceToInvoiceReqDto(Invoice invoice) {
        return InvoiceMapper.INSTANCE.getInvoiceToInvoiceReqDto(invoice);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceDao.save(invoice);
    }
}
