package com.Paisley.LibManager.LibRepo;

import com.Paisley.LibManager.LibEntity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Long> {
}
