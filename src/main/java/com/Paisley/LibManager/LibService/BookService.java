package com.Paisley.LibManager.LibService;

import com.Paisley.LibManager.LibEntity.Book;
import com.Paisley.LibManager.LibRepo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public List<Book>getAllBooks() {
        return bookRepo.findAll();
    }

    public Optional<Book>getBook(Long id){
        return bookRepo.findById(id);
    }

    public Book createBook (Book book){
        book.setAvailable(true);
       bookRepo.save(book);
        return book;
    }

    public void deleteBook(Long id){
        bookRepo.deleteById(id);
    }

}
