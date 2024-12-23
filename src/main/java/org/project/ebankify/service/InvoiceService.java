package org.project.ebankify.service;

import org.project.ebankify.dao.InvoiceDao;
import org.project.ebankify.dto.mapper.InvoiceMapper;
import org.project.ebankify.dto.request.InvoiceReqDto;
import org.project.ebankify.dto.response.InvoiceResDto;
import org.project.ebankify.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceDao invoiceDao;

    public InvoiceService(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public Invoice toInvoice(InvoiceReqDto invoiceReqDto) {
        return InvoiceMapper.INSTANCE.toInvoice(invoiceReqDto);
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
