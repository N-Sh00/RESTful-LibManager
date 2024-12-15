package com.Paisley.LibManager.LibService;

import com.Paisley.LibManager.LibEntity.Book;
import com.Paisley.LibManager.LibEntity.Loan;
import com.Paisley.LibManager.LibEntity.Member;
import com.Paisley.LibManager.LibRepo.BookRepo;
import com.Paisley.LibManager.LibRepo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    MemberRepo memberRepo;

//    @Autowired
//    LoanService loanService;

public String borrowBook (Long userId, Long bookId) {
    Member member = memberRepo.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    Book book = bookRepo.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book not found"));

    if (!book.isAvailable()) {
        return "Book already borrowed";
    }

    book.setAvailable(false);
    bookRepo.save(book);

    Loan loan = new Loan();
    loan.setUser(member);
    loan.setBook(book);
    loan.setStartDate(LocalDate.now());
    loan.setDueDate(loan.getStartDate().plusDays(10));

    return "Book borrowed successfully";
}

public String returnBook (Long userId, Long bookId) {

    Member member = memberRepo.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    Book book = bookRepo.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book not found"));

    if (book.isAvailable()) {
        return "Book not borrowed";
    }

    Loan loan = new Loan();
    loan.setReturnDate(LocalDate.now());

    book.setAvailable(true);
    bookRepo.save(book);

    return "Book returned successfully";
}
}

