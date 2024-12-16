package com.Paisley.LibManager.LibEntity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "borrow_limit")
    private int borrowLimit = 1;

    @Column(name = "books_borrowed")
    private Integer booksBorrowed = 0;

    @OneToMany(mappedBy = "borrowedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> borrowedBooks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<Integer> getBooksBorrowed() {
        return Optional.ofNullable(booksBorrowed);
    }

    public void setBooksBorrowed(int booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public void setBorrowLimit(int borrowLimit) {
        this.borrowLimit = borrowLimit;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
