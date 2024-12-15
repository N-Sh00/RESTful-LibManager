package com.Paisley.LibManager.LibController;

import com.Paisley.LibManager.LibService.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping("/api/loans/borrow/{id}/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable (value = "id") Long userId, @PathVariable (value = "bookId") Long bookId){
        String response = loanService.borrowBook(userId, bookId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/loans/return/{id}/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable (value = "id") Long userId, @PathVariable (value = "bookId") Long bookId)
    {
        String response = loanService.returnBook(userId, bookId);
        return ResponseEntity.ok(response);
    }
}
