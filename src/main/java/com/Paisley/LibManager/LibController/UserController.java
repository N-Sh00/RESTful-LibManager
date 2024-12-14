package com.Paisley.LibManager.LibController;

import com.Paisley.LibManager.LibEntity.Book;
import com.Paisley.LibManager.LibEntity.User;
import com.Paisley.LibManager.LibService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // Implement CRUD operations for User
    // Use UserService to interact with UserRep
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("api/users/{id}")
    public User getUserById(@RequestParam (value = "id") Long id) {
        return userService.getUser(id).orElse(null);
    }

    @PostMapping("/api/users")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @DeleteMapping("/api/users{id}")
    public void deleteUser(@RequestParam(value = "id") Long id) {
        userService.deleteUser(id);
    }
}
