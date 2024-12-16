package com.Paisley.LibManager.LibService;

import com.Paisley.LibManager.LibEntity.Book;
import com.Paisley.LibManager.LibEntity.Loan;
import com.Paisley.LibManager.LibEntity.Member;
import com.Paisley.LibManager.LibRepo.BookRepo;
import com.Paisley.LibManager.LibRepo.LoanRepo;
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

    private static final int borrowLimit = 1;

    @Autowired
    LoanRepo loanRepo;

public String borrowBook (Long userId, Long bookId) {
    Member member = memberRepo.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    Book book = bookRepo.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book not found"));

    if (!book.isAvailable()) {
        return "Book already borrowed";
    }

    if (member.getBorrowedBooks().size() >= member.getBorrowLimit()) {
        return "Borrow limit reached. Return a book to borrow a new one.";
    }

    book.setBorrowedBy(member);
    book.setAvailable(false);
    bookRepo.save(book);

    member.setBooksBorrowed(member.getBooksBorrowed().orElse(0) + 1);
    memberRepo.save(member);

    Loan loan = new Loan();
    loan.setUser(member);
    loan.setBook(book);
    loan.setStartDate(LocalDate.now());
    loan.setDueDate(loan.getStartDate().plusDays(10));
    loanRepo.save(loan);


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

    if (!book.getBorrowedBy().getId().equals(member.getId())) {
        return "Book not borrowed by this user";
    }

    Loan loan = new Loan();
    loan.setReturnDate(LocalDate.now());
    loanRepo.save(loan);

    book.setAvailable(true);
    book.setBorrowedBy(null);
    bookRepo.save(book);

    member.setBooksBorrowed(member.getBooksBorrowed().orElse(1) - 1);
    memberRepo.save(member);

    return "Book returned successfully";
}
}

