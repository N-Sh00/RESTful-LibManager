package com.Paisley.LibManager.controller;

import com.Paisley.LibManager.dto.BookDTO;
import com.Paisley.LibManager.dto.UpdateBookDTO;
import com.Paisley.LibManager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    private static final int ITEMS_PER_PAGE = 10;

    @GetMapping
    public ResponseEntity<Page<BookDTO>> getAllBooks(@RequestParam(defaultValue = "0") int page)
    {
        Pageable pageable = PageRequest.of(page,ITEMS_PER_PAGE);
        Page<BookDTO> books = bookService.getAllBooks(pageable);

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO book = bookService.getBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody UpdateBookDTO updateBookDTO) {
        BookDTO createdBook = bookService.createBook(updateBookDTO);
        return ResponseEntity.ok(createdBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable Long id,
            @RequestBody UpdateBookDTO updateBookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, updateBookDTO);
        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBook);
    }
}
