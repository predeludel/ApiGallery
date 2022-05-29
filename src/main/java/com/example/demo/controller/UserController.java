package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Iterable<User> read() {
        return userService.getAllUsers();
    }

    @GetMapping("/{page}/{size}")
    public Page<User> read(@PathVariable Integer page, @PathVariable Integer size) {
        return userService.getAllUsersPage(page, size);
    }

    @GetMapping("/{page}/{size}/sort/{field}")
    public Page<User> read(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String field) {
        return userService.getAllUsersPageSortByField(page, size, field);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
