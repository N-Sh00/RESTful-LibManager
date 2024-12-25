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

import java.net.URI;
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

        if (books.isEmpty()){
            return ResponseEntity.noContent().build();
        }

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
        URI location = URI.create(String.format("/api/books/%d", createdBook.getId()));
        return ResponseEntity.created(location).body(createdBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
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
