package com.Paisley.LibManager.repository;

import com.Paisley.LibManager.entity.Book;
import com.Paisley.LibManager.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Long> {
    @Override
    Page<Member> findAll(Pageable pageable);

    Member findByUsername(String username);
}
