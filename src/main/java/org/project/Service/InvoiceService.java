package org.project.Service;

import org.project.Dao.InvoiceDao;
import org.project.Dto.Mapper.InvoiceMapper;
import org.project.Dto.request.InvoiceReqDto;
import org.project.Dto.response.InvoiceResDto;
import org.project.Entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Invoice toInvoice(InvoiceResDto invoiceResDto) {
        return InvoiceMapper.INSTANCE.toInvoice(invoiceResDto);
    }

    public InvoiceReqDto getInvoiceToInvoiceReqDto(Invoice invoice) {
        return InvoiceMapper.INSTANCE.getInvoiceToInvoiceReqDto(invoice);
    }

    public InvoiceResDto getInvoiceToInvoiceResDto(Invoice invoice) {
        return InvoiceMapper.INSTANCE.getInvoiceToInvoiceResDto(invoice);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceDao.save(invoice);
    }

    public List<Invoice> getAllUserInvoices(long userId) {
        return invoiceDao.findAllByOwner_Id(userId);
    }
}
