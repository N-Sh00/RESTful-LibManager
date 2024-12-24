package com.Paisley.LibManager.mapper;

import com.Paisley.LibManager.dto.BookDTO;
import com.Paisley.LibManager.entity.Book;

import java.util.ArrayList;
import java.util.List;


public class BookMapper {
    public static BookDTO toDTO(Book book) {
        if (book == null) return null;

        BookDTO bookDTO = new BookDTO();
        bookDTO.setName(book.getName());
        bookDTO.setAvailable(book.isAvailable());
        return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAvailable(bookDTO.isAvailable());
        return book;
    }

    public static List<BookDTO> toDTOList(List<Book> books) {
        if (books == null) {
            return new ArrayList<>();
        }
            List<BookDTO> dtoList = new ArrayList<>();
            for (Book book : books) {
                dtoList.add(toDTO(book));
            }
            return dtoList;
        }

}
