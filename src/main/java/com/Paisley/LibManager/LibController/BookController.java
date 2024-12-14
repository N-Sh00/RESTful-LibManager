package com.Paisley.LibManager.LibController;

import com.Paisley.LibManager.LibEntity.Book;
import com.Paisley.LibManager.LibService.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/api/books{id}")
    public Book getBookById(@RequestParam(value = "id") Long id) {
        return bookService.getBook(id).orElse(null);
    }

    @PostMapping("/api/books")
    public void createBook(@RequestBody Book book) {
        bookService.createBook(book);
    }

    @DeleteMapping("/api/books{id}")
    public void deleteBook(@RequestParam(value = "id") Long id) {
        bookService.deleteBook(id);
    }


}
