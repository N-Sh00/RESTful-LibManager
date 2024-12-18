package com.Paisley.LibManager.LibRepo;

import com.Paisley.LibManager.LibEntity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
}
