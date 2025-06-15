package com.pdd.controller;


import com.pdd.configuration.Translator;
import com.pdd.dto.request.UserRequestDTO;
import com.pdd.dto.response.ResponseData;
import com.pdd.dto.response.ResponseError;
import com.pdd.exception.ResourceNotFoundExeption;
import com.pdd.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseData<Integer> addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), 1);
    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable @Min(1) Long userId, @Valid @RequestBody UserRequestDTO userDTO) {
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
