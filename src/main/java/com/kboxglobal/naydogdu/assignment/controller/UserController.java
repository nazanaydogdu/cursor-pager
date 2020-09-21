package com.kboxglobal.naydogdu.assignment.controller;

import com.kboxglobal.naydogdu.assignment.entity.User;
import com.kboxglobal.naydogdu.assignment.paging.CursorPager;
import com.kboxglobal.naydogdu.assignment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<CursorPager<User>> getUsers(@RequestParam(value = "next", defaultValue = "", required = false) String next) {
        return ResponseEntity.ok(userService.getAllUsers(next));
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
