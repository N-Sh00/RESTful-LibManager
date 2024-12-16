package com.Paisley.LibManager.LibController;

import com.Paisley.LibManager.LibService.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping
    public ResponseEntity<String> borrowBook(@RequestBody Map<String, Object> data) {
        Long userId = Long.valueOf(data.get("userId").toString());
        Long bookId = Long.valueOf(data.get("bookId").toString());
        String response = loanService.borrowBook(userId, bookId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<String> returnBook(@RequestBody Map<String, Object> data) {
        Long userId = Long.valueOf(data.get("userId").toString());
        Long bookId = Long.valueOf(data.get("bookId").toString());
        String response = loanService.returnBook(userId, bookId);
        return ResponseEntity.ok(response);
    }
}
