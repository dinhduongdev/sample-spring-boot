package com.pdd.controller;


import com.pdd.dto.request.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/")
    public String addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        return "User added";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDTO userDTO) {
        System.out.println("Request update userId: " + userId);
        return "User updated";
    }

    @PatchMapping("/{userId}")
    public String changeStatus(@PathVariable Long userId, @RequestParam(required = false) Boolean status) {
        System.out.println("Request change status userId: " + userId);
        return "User status changed";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        System.out.println("Request delete userId: " + userId);
        return "User deleted";
    }

    @GetMapping("/{userId}")
    public UserRequestDTO getUser(@PathVariable Long userId) {
        System.out.println("Request get user by userId: " + userId);
        return new UserRequestDTO("firstName", "lastName", "email", "password");
    }

    @GetMapping("/list")
    public List<UserRequestDTO> getAllUsers(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        System.out.println("Request list all users");
        return List.of(new UserRequestDTO("firstName", "lastName", "email", "password"));
    }
}
