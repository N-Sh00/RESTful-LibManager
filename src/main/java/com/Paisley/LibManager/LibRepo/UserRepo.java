package com.Paisley.LibManager.LibRepo;

import com.Paisley.LibManager.LibEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
