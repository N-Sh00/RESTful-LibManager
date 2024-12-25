package com.Paisley.LibManager.controller;

import com.Paisley.LibManager.entity.Loan;
import com.Paisley.LibManager.service.LoanService;
import com.Paisley.LibManager.dto.LoanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping
    public ResponseEntity<String> borrowBook(@RequestBody LoanEntity loanEntity) {
        String response = loanService.borrowBook(loanEntity.getUserId(), loanEntity.getBookId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<String> returnBook(@RequestBody LoanEntity loanEntity) {
        String response = loanService.returnBook(loanEntity.getUserId(), loanEntity.getBookId());
        return ResponseEntity.ok(response);
    }
}
