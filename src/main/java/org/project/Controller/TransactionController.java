package org.project.Controller;

import org.project.Dto.request.TransactionReqDto;
import org.project.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewTransaction(@RequestBody TransactionReqDto transactionReqDto) {

    }

    @GetMapping("/")
    public ResponseEntity<?> getUserTransactionHistory(@RequestBody ) {
        return null;
    }
}
