package org.project.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.project.Dto.request.InvoiceReqDto;
import org.project.Entity.Invoice;
import org.project.Service.InvoiceService;
import org.project.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceReqDto invoiceReqDto) {
        Invoice invoice = invoiceService.toInvoice(invoiceReqDto);
        invoiceService.saveInvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body("Invoice created!");
    }

    @GetMapping("/all")
    public ResponseEntity<?> viewInvoiceHistory(HttpServletRequest request) {
        Long userId = (Long) request.getSession(false).getAttribute("AUTH.id");
        List<Invoice> invoices = invoiceService.getAllUserInvoices(userId);
        List<InvoiceViewModel> invoiceViewModels = invoices.stream()
                .map(invoiceService::getInvoiceToInvoiceResDto)
                .map(InvoiceViewModel::new)
                .toList();
        return ResponseEntity.ok(invoiceViewModels);
    }
}
