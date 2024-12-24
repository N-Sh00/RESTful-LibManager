package com.Paisley.LibManager.service;

import com.Paisley.LibManager.dto.BookDTO;
import com.Paisley.LibManager.dto.UpdateBookDTO;
import com.Paisley.LibManager.entity.Book;
import com.Paisley.LibManager.entity.Member;
import com.Paisley.LibManager.mapper.BookMapper;
import com.Paisley.LibManager.repository.BookRepo;
import com.Paisley.LibManager.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    MemberRepo memberRepo;


    public Page<BookDTO> getAllBooks(Pageable pageable) {
        Page<Book> bookPage = bookRepo.findAll(pageable);
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : bookPage.getContent()) {
            BookDTO bookDTO = mapToDTO(book);
            bookDTOs.add(bookDTO);
        }
        return new PageImpl<>(bookDTOs, pageable, bookPage.getTotalElements());
    }


    public BookDTO getBook(Long id) {
        Book book = bookRepo.findById(id).orElse(null);
        if (book == null) {
            return null;
        }
        return mapToDTO(book);
    }

    public BookDTO createBook(UpdateBookDTO updateBookDTO) {
        Book book = mapToEntity(updateBookDTO);
        Book savedBook = bookRepo.save(book);
        return mapToDTO(savedBook);
    }

    public void deleteBook(Long id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
        } else {
            throw new RuntimeException("Book with id " + id + " not found");
        }
    }

    public BookDTO updateBook(Long id, UpdateBookDTO updateBookDTO) {
        Book existingBook = bookRepo.findById(id).orElse(null);
        if (existingBook == null) {
            return null;
        }
        existingBook.setName(updateBookDTO.getName());
        existingBook.setAvailable(updateBookDTO.getIsAvailable());
        if (updateBookDTO.getName() != null) {
            existingBook.setName(updateBookDTO.getName());
        }
        if (updateBookDTO.getBorrowedById() != null) {
            Member member = memberRepo.findById(updateBookDTO.getBorrowedById()).orElse(null);
            existingBook.setBorrowedBy(member);
        } else {
            existingBook.setBorrowedBy(null);
        }
        Book updatedBook = bookRepo.save(existingBook);
        return mapToDTO(updatedBook);
    }


    private BookDTO mapToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAvailable(book.isAvailable());
        if (book.getBorrowedBy() != null) {
            bookDTO.setBorrowedById(book.getBorrowedBy().getId());
        } else {
            bookDTO.setBorrowedById(null);
        }
        return bookDTO;
    }





    private Book mapToEntity(UpdateBookDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setAvailable(dto.getIsAvailable());
        if (dto.getBorrowedById() != null) {
            Member member = memberRepo.findById(dto.getBorrowedById()).orElse(null);
            book.setBorrowedBy(member);
        }
        return book;
    }

}

