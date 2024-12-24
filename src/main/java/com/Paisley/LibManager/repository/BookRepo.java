package com.Paisley.LibManager.repository;

import com.Paisley.LibManager.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    @Override
    Page<Book> findAll(Pageable pageable);
}
