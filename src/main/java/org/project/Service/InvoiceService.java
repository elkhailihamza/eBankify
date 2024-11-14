package org.project.Service;

import org.project.Dao.InvoiceDao;
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
}
