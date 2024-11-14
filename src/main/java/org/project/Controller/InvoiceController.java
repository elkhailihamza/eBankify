package org.project.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.project.Dto.request.InvoiceReqDto;
import org.project.Entity.Invoice;
import org.project.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> viewInvoiceHistory(@RequestBody InvoiceReqDto invoiceReqDto, HttpServletRequest request) {
        return null;
    }
}
