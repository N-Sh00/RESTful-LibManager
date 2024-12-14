package com.Paisley.LibManager.LibService;

import com.Paisley.LibManager.LibEntity.User;
import com.Paisley.LibManager.LibRepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }

    public void createUser(User user){
        userRepo.save(user);
    }


    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
