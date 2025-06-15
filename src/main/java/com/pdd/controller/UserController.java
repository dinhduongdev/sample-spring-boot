package com.pdd.controller;


import com.pdd.dto.request.UserRequestDTO;
import com.pdd.dto.response.ResponseData;
import com.pdd.dto.response.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {



    @PostMapping("/")
    public ResponseData<Integer> addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        return new ResponseData<>(HttpStatus.CREATED.value(), "User added successfully", 1);
    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDTO userDTO) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully");
    }

    @PatchMapping("/{userId}")
    public ResponseData<?> changeStatus(@PathVariable Long userId, @RequestParam(required = false) Boolean status) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User status changed successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted successfully");
    }

    @GetMapping("/{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "User retrieved successfully",
                new UserRequestDTO("firstName", "lastName", "phone", "email"));
    }

    @GetMapping("/list")
    public ResponseData<List<UserRequestDTO>> getAllUsers(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ResponseData<>(
                HttpStatus.OK.value(),
                "list",
                List.of(new UserRequestDTO("firstName", "lastName", "phone", "email")));
    }
}
