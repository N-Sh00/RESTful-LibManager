package com.Paisley.LibManager.LibController;

import com.Paisley.LibManager.LibEntity.Book;
import com.Paisley.LibManager.LibService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/api/books/{id}")
    public Book getBookById(@PathVariable(value = "id") Long id) {
        return bookService.getBook(id).orElse(null);
    }

    @PostMapping("/api/books/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return ResponseEntity.ok(createdBook);
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        bookService.deleteBook(id);
    }


}
