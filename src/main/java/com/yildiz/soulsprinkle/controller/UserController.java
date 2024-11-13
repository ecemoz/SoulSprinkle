package com.yildiz.soulsprinkle.controller;

import com.yildiz.soulsprinkle.model.User;
import com.yildiz.soulsprinkle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User>getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User>getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User>getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/name")
    public ResponseEntity<User>getUserByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname) {
        return userService.getUserByFirstnameAndLastname(firstname , lastname)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/username-email")
    public ResponseEntity<User>getUserByEmailAndUsername(@RequestParam String email, @RequestParam String username) {
        return userService.getUserByEmailAndUsername(email,username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/with-picture")
    public ResponseEntity<List<User>>getUserWithPicture() {
        List<User> usersWithPictureUrl = userService.getUserWithPicture();
        return new ResponseEntity<>(usersWithPictureUrl, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User>updateUser(@PathVariable Long id , @RequestBody User updatedUser) {
        try {
            User user = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteUserById(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<User>getAllUsers() {
        return userService.getAllUsers();
    }
}